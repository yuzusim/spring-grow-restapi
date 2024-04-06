package shop.mtcoding.blog.domain.resume;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.domain.user.UserRequest;

@Import(ResumeService.class)
@DataJpaTest
public class ResumeServiceTest {
    @Autowired
    private ResumeService resumeService;



//    @Test
//    public void ResumeApplyDTO_test() {
//        // given
//        int resumeId = 1;
//        int jobsId = 3;
//        // when
//        resumeService.findAllResumeJoinApplyByUserIdAndJobsId(resumeId, jobsId);
//        // then
//
//    }
}
