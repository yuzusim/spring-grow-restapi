package shop.mtcoding.blog.domain.jobs;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.domain.skill.Skill;
import shop.mtcoding.blog.domain.skill.SkillJPARepository;
import shop.mtcoding.blog.domain.user.User;
import shop.mtcoding.blog.domain.user.UserJPARepository;

import java.util.ArrayList;
import java.util.List;

@Import(JobsService.class)
@DataJpaTest
public class JobsServiceTest {
    @Autowired
    private JobsJPARepository jobsRepo;
    @Autowired
    private UserJPARepository userRepo;
    @Autowired
    private SkillJPARepository skillRepo;
    @Autowired
    private JobsService jobsService;

    @Test
    public void keyword_test() {
        // given
        String keyword = "백엔드";
        // when
        jobsService.searchKeyword(keyword);
        // then

    }

    @Test
    public void search_test() {
        // given
        String keyword = "네이버";
        // when
        jobsService.searchKeyword(keyword);
        // then

    }


    @Test
    public void detailDTO_test() {
        // given
        int jobsId = 1;
        // when
        Jobs jobs = jobsRepo.findById(jobsId)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));
        User user = userRepo.findById(jobs.getUser().getId())
                .orElseThrow(() -> new Exception404("해당 사용자를 찾을 수 없습니다."));
        List<Skill> skillList = skillRepo.findAllById(jobs.getId());

        JobsResponse.DetailDTO detailDTO = new JobsResponse.DetailDTO(jobs, user, skillList);
        // then
        System.out.println(detailDTO.toString());
    }

    @Test
    public void listDTOS_test() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Jobs> jobsList = jobsRepo.findAll(sort);

        List<JobsResponse.ListDTO> listDTOS = new ArrayList<>();

        for (int i = 0; i < jobsList.size(); i++) {
            User user = userRepo.findById(jobsList.get(i).getUser().getId())
                    .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));

            List<Skill> skillList = skillRepo.findAllById(jobsList.get(i).getId());
            listDTOS.add(JobsResponse.ListDTO.builder()
                    .jobs(jobsList.get(i))
                    .user(user)
                    .skills(skillList).build());
        }
    }
}
