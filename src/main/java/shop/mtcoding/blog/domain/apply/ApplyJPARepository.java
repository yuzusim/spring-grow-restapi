package shop.mtcoding.blog.domain.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    //select * from apply_tb a join jobs_tb j on a.jobs_id = j.id where a.resume_id = 1;
    @Query("select j from Apply a join fetch Jobs j on a.jobs.id = j.id where a.resume.id = :resumeId")
    List<Apply> findAllByJobsIdAndUserId(@Param("resumeId") Integer resumeId);

    @Query("select a from Apply a where a.resume.id = :resumeId")
    Optional<Apply> findByResumeId(@Param("resumeId") Integer resumeId);

    @Query("select a from Apply a where a.resume.id = :resumeId and a.jobs.id = :jobsId")
    Optional<Apply> findByResumeIdAndJobsId(@Param("resumeId") Integer resumeId, @Param("jobsId") Integer jobsId);

    //현재 이력서로 공고에 지원 중인 이력서를 모두 조회
    @Query("select a from Apply a where a.isPass not in ('1') and a.resume.id = :resumeId")
    List<Apply> findAppliesByNot1ByResumeId (@Param("resumeId") Integer resumeId);

    @Query("select a, r, u FROM Apply a join fetch Resume r on a.resume.id = r.id join fetch User u on u.id = r.user.id WHERE a.jobs.id = :jobsId")
    List<Apply> findAllByJobsId(@Param("jobsId") Integer jobsId);

    @Query("select a from Apply a where a.jobs.id = :jobsId and a.resume.id = :resumeId")
    Optional<Apply> findByJidRid(@Param("jobsId") Integer jobsId, @Param("resumeId") Integer resumeId);

    @Query("select a from Apply a where a.isPass not in ('1') and a.jobs.id = :jobsId")
    List<Apply> findAllByJidAn1(@Param("jobsId") Integer jobsId);

    // 기업사용자의 모든 공고 지원한 모든 지원자 - 총 지원자 현황 구하기 - 중복도 제거
    @Query("select a from Apply a where a.isPass not in ('1') and a.jobs.user.id= :userId")
    List<Apply> findAllByUidN1(@Param("userId") Integer userId);

    // 기업사용자의 모든 공고 지원한 모든 지원자 - 미응답 현황 구하기
    @Query("select a from Apply a where a.isPass in ('2') and a.jobs.user.id= :userId")
    List<Apply> findAllByUidI2(@Param("userId") Integer userId);

    // 세션유저가 지원한 신청리스트
    @Query("select a from Apply a where a.isPass not in ('1') and a.resume.user.id = :userId")
    List<Apply> findAllUserByApply(@Param("userId") Integer userId);

    //세션유저가 이력서로 신청한 적 있는지 확인하기 위해서
    @Query("select a from Apply a join fetch Resume r on a.resume.id = r.id join fetch Jobs j on a.jobs.id = j.id where r.id = :resumeId and j.id = :jobsId")
    Apply findApplyByResumeId(@Param("resumeId") Integer resumeId,@Param("jobsId") Integer jobsId);




}

