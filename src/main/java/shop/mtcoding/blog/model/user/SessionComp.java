package shop.mtcoding.blog.model.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SessionComp {
    private Integer id;
    private String username;
    private String email;
    private Integer role;
    private LocalDate createdAt;

    @Builder
    public SessionComp(Integer id, String username, String email, Integer role, LocalDate createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }
}


