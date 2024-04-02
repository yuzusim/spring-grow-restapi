package shop.mtcoding.blog.model.scrap;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name="scrap_tb")
@Data
@Entity
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Jobs jobs;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdAt;

    @Builder
    public Scrap(Integer id, User user, Resume resume, Jobs jobs, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.resume = resume;
        this.jobs = jobs;
        this.createdAt = createdAt;
    }
}
