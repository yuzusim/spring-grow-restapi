package shop.mtcoding.blog.domain.skill;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.domain.jobs.Jobs;
import shop.mtcoding.blog.domain.resume.Resume;

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
    private String color;

    @Builder
    public Skill(Integer id, Resume resume, Jobs jobs, String name, String color) {
        this.id = id;
        this.resume = resume;
        this.jobs = jobs;
        this.name = name;
        this.color = color;
    }

    public Skill.DTO toDTO(Skill skill){
        return DTO.builder()
                .skill(skill).build();
    }

    @Data
    public class DTO{
        private Integer id;
        private String name;

        @Builder
        public DTO(Skill skill) {
            this.id = skill.getId();
            this.name = skill.getName();
        }
    }

}
