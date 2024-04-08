package shop.mtcoding.blog.domain.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(ApplyService.class)
@DataJpaTest
public class ApplyServiceTest {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private ApplyJPARepository applyJPARepo;



    @Test
    public void cancel_test(){
        // given
        Integer jobsId = 1;
        Integer resumeId=1;
        // when
        applyService.cancel(jobsId, resumeId);
        // then

    }
}
