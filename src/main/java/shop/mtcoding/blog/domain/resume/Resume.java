package shop.mtcoding.blog.domain.resume;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.domain.apply.Apply;
import shop.mtcoding.blog.domain.skill.Skill;
import shop.mtcoding.blog.domain.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "resume_tb")
@Getter
@Setter
@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String title;
    private String area;
    private String edu;
    private String career;
    private String introduce;
    private String portLink;
    @Transient
    private boolean isOwner;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Skill> skillList = new ArrayList<>();

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Apply> applyList = new ArrayList<>();

    @CreationTimestamp //pc -> db 날짜주입
    private Timestamp createdAt;

    @Builder
    public Resume(Integer id, User user, String title, String area, String edu, String career, String introduce, String portLink, boolean isOwner, List<Skill> skillList, List<Apply> applyList, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.area = area;
        this.edu = edu;
        this.career = career;
        this.introduce = introduce;
        this.portLink = portLink;
        this.isOwner = isOwner;
        this.skillList = skillList;
        this.applyList = applyList;
        this.createdAt = createdAt;
    }
}
