package shop.mtcoding.blog.model.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.model.jobs.Jobs;

import java.util.List;

public interface SkillJPARepository extends JpaRepository<Skill, Integer> {

    @Query("select s from Skill s where s.jobs.id = :jobsId")
    List<Skill> findAllById(@Param("jobsId") Integer jobsId);

    @Query("select s from Skill s where s.jobs.id = :jobsId")
    List<Skill> findAllByJobsId(@Param("jobsId") Integer jobsId);

    /***
     * clearAutomatically : 쿼리 실행 후 영속성 컨텍스트를 자동으로 지움
     * flushAutomatically : 자동으로 플러시함
     * 영속성 컨텍스트를 지우면 영속성 컨텍스트에 있는 모든 엔티티가 제거됨
     * 플러시는 변경 내용을 데이터 베이스에 동기화함.
     * 삭제 하는데 쿼리가 자꾸 오류나서 알아보니 저거 써줘야 하는듯 하여 작성함
     * 그냥 deleteById 하면 id만 지움 jobsId를 찾아서 지우려면
     * 쿼리를 직접 작성하여야 함
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Skill s where s.jobs.id = :jobsId")
    void deleteByJobsId(@Param("jobsId") Integer jobsId);


    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Skill s where s.resume.id = :resumeId")
    void deleteByresumeId(@Param("resumeId") Integer resumeId);

    @Query("select s from Skill s where s.resume.id = :resumeId")
    List<Skill> findAllByResumeId(@Param("resumeId") Integer resumeId);


    @Query("select s from Apply a join fetch Resume r on a.resume.id = r.id join fetch Skill s on s.resume.id = r.id where a.id = :resumeId")
    List<Skill> findAllByJoinResumeId(@Param("resumeId") Integer resumeId);

    @Query("select s from Jobs j join fetch User u on j.user.id = u.id join fetch Skill s on s.jobs.id = j.id where j.id = :jobsId")
    List<Skill> findAllByJoinJobsId(@Param("jobsId") Integer jobsId);

    @Query("SELECT s FROM Skill s WHERE s.jobs.id = :jobsId")
    List<Skill> findByJobsId(@Param("jobsId") Integer jobsId);
}

