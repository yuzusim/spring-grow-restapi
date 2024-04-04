package shop.mtcoding.blog.domain.comp;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.blog.domain.user.User;

public interface CompJPARepository extends JpaRepository <User, Integer>{

//    // 전에거에 있던 이메일 찾아서 그걸로 세션저장해서 회원가입 직후 바로 로그인 되는거 구현하려고 만듬
//    @Query("select u from User u where u.email = :email")
//    User findByEmail(@Param("email") String email);
}
