package shop.mtcoding.blog.domain.skill;


import jakarta.persistence.*;
import lombok.*;
import shop.mtcoding.blog.domain.jobs.Jobs;
import shop.mtcoding.blog.domain.resume.Resume;
import shop.mtcoding.blog.domain.resume.ResumeResponse;

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

    public ResumeResponse.SkillDTO toDTO(){

        String colorClass = "";
        if (name.equals("Jquery")){
            colorClass = "badge bg-primary";
        }
        else if(name.equals("JavaScript")){
            colorClass = "badge bg-secondary";
        }
        else if(name.equals("Spring")){
            colorClass = "badge bg-success";
        }
        else if(name.equals("HTML/CSS")){
            colorClass = "badge bg-danger";
        }
        else if(name.equals("JSP")){
            colorClass = "badge bg-warning";
        }
        else if(name.equals("Java")){
            colorClass = "badge bg-info";
        }
        else if(name.equals("React")){
            colorClass = "badge bg-dark";
        }
        else if(name.equals("Oracle")){
            colorClass = "badge bg-info";
        }
        else if(name.equals("Vue.js")){
            colorClass = "badge bg-info";
        }
        else if(name.equals("MySql")){
            colorClass = "badge bg-info";
        }

        return ResumeResponse.SkillDTO.builder()
            .id(this.id)
            .name(this.name)
            .color(colorClass)
            .build();
    }




}
