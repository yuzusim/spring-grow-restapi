package shop.mtcoding.blog.model.jobs;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "jobs_tb")
@Getter
@Setter
@Entity
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String area;

    private String title;

    private String edu;

    private String career;

    private String content;

    // 이거 머스태치 LocalDate만 인식함..
    private LocalDate deadline;

    private String task;

    @CreationTimestamp
    private Timestamp createdAt;

    @Transient
    private Boolean isOwner;
    @OneToMany(mappedBy = "jobs", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Skill> skillList = new ArrayList<>();

    @OneToMany(mappedBy = "jobs", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Apply> applyList = new ArrayList<>();


    @Builder
    public Jobs(Integer id, User user, String area, String title, String edu, String career, String content, LocalDate deadline, String task, Timestamp createdAt, Boolean isOwner, List<Skill> skillList, List<Apply> applyList) {
        this.id = id;
        this.user = user;
        this.area = area;
        this.title = title;
        this.edu = edu;
        this.career = career;
        this.content = content;
        this.deadline = deadline;
        this.task = task;
        this.createdAt = createdAt;
        this.isOwner = isOwner;
        this.skillList = skillList;
        this.applyList = applyList;
    }
}

