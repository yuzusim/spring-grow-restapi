package shop.mtcoding.blog.domain.skill;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.domain.jobs.Jobs;
import shop.mtcoding.blog.domain.resume.Resume;
import shop.mtcoding.blog.domain.resume.ResumeRequest;

@NoArgsConstructor
@Table(name = "skill_tb")
@Data
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "resume_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @JoinColumn(name = "jobs_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Jobs jobs;

    @Column(nullable = false)
    private String name;

    @Builder
    public Skill(Integer id, Resume resume, Jobs jobs, String name) {
        this.id = id;
        this.resume = resume;
        this.jobs = jobs;
        this.name = name;
    }


    public Skill nameToEntity(String name){
        this.name = name;
        return this;
    }

    public Skill.DTO toDTO(Skill skill){

        return DTO.builder().skill(skill).build();
    }


    public Skill toEntity(SkillDTO updateResumeDTO){
        this.id = updateResumeDTO.getId();
        this.name = updateResumeDTO.getName();
        return this;
    }

    @Data
    public class SkillDTO extends Skill {
        private Integer id;
        private String name;

        public SkillDTO(Skill skill) {
            this.id = id;
            this.name = name;
        }
    }


    @Data
    public static class DTO{
        private Integer id;
        private String name;

        @Builder
        public DTO (Skill skill) {
            this.id = skill.getId();
            this.name = skill.getName();
        }
    }
}
