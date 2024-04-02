package shop.mtcoding.blog.model.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {

    @Query("select r from Resume r join fetch r.user u order by r.id desc")
    List<Resume> findAllJoinUser();

    @Query("select r,u,s from Resume r join fetch r.user u join fetch r.skillList s order by r.id desc")
    List<Resume> findAllJoinUserAndSkill();

    @Query("select r from Resume r where r.id = :id order by r.id desc")
    List<Resume> findAllUserId(@Param("id") Integer id);

    @Query("select r from Resume r where r.user.id = :userId")
    List<Resume> findAllByUserId(@Param("userId") Integer userId);

    @Query("select r from Resume r join fetch r.user u where r.id = :resumeId")
    Resume findByIdJoinUser(@Param("resumeId") Integer resumeId);
}
