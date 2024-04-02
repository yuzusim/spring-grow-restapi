package shop.mtcoding.blog.model.comp;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.model.user.User;

@Import(CompService.class)
@DataJpaTest
public class CompServiceTest {
    @Autowired
    private CompService compService;

    @Autowired
    private HttpSession session;
    @Test
    public void findApplicants_test(){
        // given
        int jobsId = 1;
        // when
        compService.findApplicants(jobsId);
        // then
    }

    @Test
    public void findAllResumeUserSKill_test(){
        // given
        User sessionComp = (User) session.getAttribute("sessionComp");
        // when
        compService.findAllRusList();
        // then

    }
}
