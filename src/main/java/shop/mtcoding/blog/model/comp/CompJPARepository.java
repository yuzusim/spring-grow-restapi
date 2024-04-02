package shop.mtcoding.blog.model.comp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

public interface CompJPARepository extends JpaRepository <User, Integer>{

    // 전에거에 있던 이메일 찾아서 그걸로 세션저장해서 회원가입 직후 바로 로그인 되는거 구현하려고 만듬
    User findByEmail(@Param("email") String email);

    @Query("select j from Jobs j where j.user.id = :userId")
    List<Jobs> findAllByUserId (@Param("userId")Integer userId);

    @Query("select j from Jobs j join fetch j.skillList where j.user.id = :userId")
    List<Jobs> findAllByUserIdWithSkill(@Param("userId") Integer userId);
}
