package shop.mtcoding.blog.domain.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ApplyJPARepositoryTest {
    @Autowired
    private ApplyJPARepository applyJPARepo;


    @Test
    public void findByRIdJIdUserSkills_Test(){
        // given

        // when
        Optional<Apply> apply = applyJPARepo.findByRIdJIdUserSkills(1,1);
        // then
        if (apply.isPresent()){
            System.out.println(apply.get().getResume().getUser().getMyName());
        }
    }

    @Test
    public void q_test(){
        // given
        int resumeId = 1;
        // when
        List<Apply> applyList = applyJPARepo.findAppliesByNot1ByResumeId(resumeId);
        // then


    }
}
