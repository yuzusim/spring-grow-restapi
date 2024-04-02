package shop.mtcoding.blog.model.jobs;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.skill.SkillResponse;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserJPARepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobsService {
    private final JobsJPARepository jobsRepo;
    private final UserJPARepository userRepo;
    private final SkillJPARepository skillRepo;

    public List<Jobs> searchKeyword(String keyword) {

        List<Jobs> jobsList;

        if (keyword.isBlank()) {
            jobsList = jobsRepo.findAll();

        } else {
            jobsList = jobsRepo.findAllKeyword(keyword);
        }

        return jobsList;

    }

    public JobsResponse.DetailDTO DetailDTO(Integer jobsId, User sessionUser) {

        Jobs jobs = jobsRepo.findById(jobsId)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));
        User user = userRepo.findById(jobs.getUser().getId())
                .orElseThrow(() -> new Exception404("해당 사용자를 찾을 수 없습니다."));
        List<Skill> skillList = skillRepo.findAllById(jobs.getId());


        JobsResponse.DetailDTO detailDTO = new JobsResponse.DetailDTO(jobs, user, skillList);
        Boolean isOwner = false;

        if (sessionUser.getRole() == 2) {
            isOwner = true;
        }

        detailDTO.setIsOwner(isOwner);

        return detailDTO;
    }

    public List<JobsResponse.ListDTO> listDTOS() {
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
        return listDTOS;
    }

    @Transactional
    public Jobs save(User sessionComp, JobsRequest.JobsSaveDTO reqDTO) {
        // 공고작성 하기
        Jobs jobs = reqDTO.toEntity(reqDTO, sessionComp);
        return jobsRepo.save(jobs);
    }

    @Transactional
    public void delete(Integer id) {
        jobsRepo.deleteById(id);
    }

    @Transactional
    public JobsResponse.UpdateDTO update(Integer id, JobsRequest.UpdateDTO reqDTO, User sessionComp) {
        Jobs jobs = jobsRepo.findById(id)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));

        if (sessionComp.getId() != jobs.getUser().getId()) {
            throw new Exception403("공고를 수정할 권한이 없습니다");
        }

        jobs.setTitle(reqDTO.getTitle());
        jobs.setEdu(reqDTO.getEdu());
        jobs.setCareer(reqDTO.getCareer());
        jobs.setContent(reqDTO.getContent());
        jobs.setArea(reqDTO.getArea());
        jobs.setDeadline(reqDTO.getDeadLine());
        jobs.setTask(reqDTO.getTask());

        System.out.println(reqDTO.getArea());

        skillRepo.deleteByJobsId(id);

        reqDTO.getSkill().stream()
                .map((skill) -> {
                    return skill.toEntity(jobs);
                })
                .forEach((skill) -> {
                    skillRepo.save(skill);
                });

        List<Skill> skill = skillRepo.findByJobsId(jobs.getId());

        return new JobsResponse.UpdateDTO(jobs, skill);
    }

    public JobsResponse.JobUpdateDTO updateForm(Integer id, Integer SessionCompId) {
        Jobs jobs = jobsRepo.findById(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));


        // 여기 바뀜
        if (SessionCompId != jobs.getUser().getId()) {
            throw new Exception403("이력서를 수정할 권한이 없습니다");
        }


        // 찾아오게 바꿈
        User user = userRepo.findById(jobs.getUser().getId())
                .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));

        // 찾아오게 바꿈
        List<Skill> skillList = skillRepo.findByJobsId(id);

        JobsResponse.JobUpdateDTO reqDTO = JobsResponse.JobUpdateDTO.builder()
                .jobs(jobs)
                .user(user)
                .skills(skillList)
                .build();

        return reqDTO;
    }
}

