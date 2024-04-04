package shop.mtcoding.blog.domain.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.domain.jobs.JobsService;
import shop.mtcoding.blog.domain.resume.Resume;
import shop.mtcoding.blog.domain.resume.ResumeJPARepository;
import shop.mtcoding.blog.domain.skill.Skill;
import shop.mtcoding.blog.domain.skill.SkillJPARepository;

import java.util.ArrayList;
import java.util.List;

@Import(UserService.class)
@DataJpaTest
public class UserServiceTest {
    @Autowired
    private UserJPARepository userJPARepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ResumeJPARepository resumeRepo;
    @Autowired
    private SkillJPARepository skillRepo;
    @Autowired
    private UserJPARepository userJPARepo;
    @Autowired
    private JobsService jobsService;





    @Test
    public void UserResumeSkillDTO_test() {
        //given
        int userId =1;
        //when
        List<UserResponse.UserResumeSkillDTO> ursList = new ArrayList<>();
        // 사용자 id로 조회된 모든 이력서를 찾는다.
        List<Resume> resumeList = resumeRepo.findAllByUserId(userId);

        User user = userJPARepo.findById(userId)
                .orElseThrow(() -> new Exception401("사용자를 찾을 수 없습니다."));

        for (int i = 0; i < resumeList.size(); i++) {  //사용자가 가지고 있는 resume를 기준으로 필요한 정보를 주입한다.
            List<Skill> skills = skillRepo.findAllByResumeId(resumeList.get(i).getId());
            ursList.add(UserResponse.UserResumeSkillDTO.builder()
                    .user(user)
                    .resume(resumeList.get(i))
                    .skillList(skills).build());

            System.out.println(resumeList.get(i).getId());
        }

    }
    @Test
    public void join_test(){
        // given
        Integer role = 1;
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setEmail("chugue@naver.com");
        reqDTO.setMyName("chugue");
        reqDTO.setPassword("1234");
        reqDTO.setPhone("01034220935");
        // when
        userService.join(reqDTO, role);
        // then

    }

    @Test
    public void login_test(){
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setEmail("bluepig4b@naver.com");
        reqDTO.setPassword("1234");
        // when
        User user = userJPARepository.findByIdAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("회원 정보가 없습니다."));
        // then
        System.out.println(user.getEmail());
    }
}
