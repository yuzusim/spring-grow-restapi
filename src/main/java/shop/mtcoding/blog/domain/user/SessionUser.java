package shop.mtcoding.blog.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionUser {
    private Integer id;
    private String username;
    private String email;
    private Integer role;

    @Builder
    public SessionUser(Integer id, String username, String email, Integer role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}


