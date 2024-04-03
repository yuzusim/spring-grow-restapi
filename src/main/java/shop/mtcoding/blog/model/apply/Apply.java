package shop.mtcoding.blog.model.apply;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.resume.Resume;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "apply_tb")
@Data
@Entity
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Jobs jobs;


    // 1. 지원안함 2. 지원중  3.합격 4.불합격
    @Column(nullable = false)
    private String isPass;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Apply(Integer id, Resume resume, Jobs jobs, String isPass, Timestamp createdAt) {
        this.id = id;
        this.resume = resume;
        this.jobs = jobs;
        this.isPass = isPass;
        this.createdAt = createdAt;
    }
}
