package shop.mtcoding.blog.model.resume;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Resume(Integer id, User user, String title, String area, String edu, String career, String introduce, String portLink, boolean isOwner, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.area = area;
        this.edu = edu;
        this.career = career;
        this.introduce = introduce;
        this.portLink = portLink;
        this.isOwner = isOwner;
        this.createdAt = createdAt;
    }

    public ResumeResponse.ResumeDTO toDTO() {
        return ResumeResponse.ResumeDTO.builder()
                .id(this.id)
                .user(user.toDTO())
                .title(this.title)
                .area(this.area)
                .edu(this.edu)
                .career(this.career)
                .introduce(this.introduce)
                .portLink(this.portLink)
                .createdAt(this.createdAt.toLocalDateTime().toLocalDate())
                .skillList(skillList.stream().map(skill -> skill.toDTO())
                        .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", area='" + area + '\'' +
                ", edu='" + edu + '\'' +
                ", career='" + career + '\'' +
                ", introduce='" + introduce + '\'' +
                ", portLink='" + portLink + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
