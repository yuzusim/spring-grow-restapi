package shop.mtcoding.blog.domain.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {

    @Query("select r from Resume r join fetch r.user u order by r.id desc")
    List<Resume> findAllJoinUser();

    @Query("select r from Resume r where r.id = :id order by r.id desc")
    List<Resume> findAllUserId(@Param("id") Integer id);

    @Query("""
            select r 
            from Resume r
            join fetch r.user 
            join fetch r.skillList s 
            where r.user.id = :userId""")
    List<Resume> findAllByUserId(@Param("userId") Integer userId);

    @Query("select r from Resume r join fetch r.user u where r.id = :resumeId")
    Resume findByIdJoinUser(@Param("resumeId") Integer resumeId);

    @Query("select r from Resume r join fetch r.user u join fetch r.skillList s where r.id = :resumeId")
    Resume findByResumeIdJoinUserWithSkills (@Param("resumeId") Integer resumeId);

    @Query("select r from Resume r join fetch Apply a on a.resume.id = r.id join fetch User u on r.user.id = u.id where a.isPass in ('1') and r.user.id = :userId")
    List<Resume> findAllDetailResumeByUserId(@Param("userId") Integer userId);

    @Query("select r from Resume r join fetch r.user u left join fetch r.skillList s")
    List<Resume> findAllMainList();

    @Query("select r from Resume r join fetch r.skillList s where r.id = :resumeId")
    Optional<Resume> findByIdWithSkills(@Param("resumeId") Integer resumeId);
}
