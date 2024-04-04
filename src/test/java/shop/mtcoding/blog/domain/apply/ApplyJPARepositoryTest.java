package shop.mtcoding.blog.domain.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ApplyJPARepositoryTest {
    @Autowired
    private ApplyJPARepository applyJPARepo;

    @Test
    public void q_test(){
        // given
        int resumeId = 1;
        // when
        List<Apply> applyList = applyJPARepo.findAppliesByNot1ByResumeId(resumeId);
        // then

        System.out.println(applyList.size());

    }
}
