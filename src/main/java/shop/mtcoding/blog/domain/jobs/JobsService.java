package shop.mtcoding.blog.domain.jobs;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.domain.apply.Apply;
import shop.mtcoding.blog.domain.apply.ApplyJPARepository;
import shop.mtcoding.blog.domain.resume.Resume;
import shop.mtcoding.blog.domain.resume.ResumeJPARepository;
import shop.mtcoding.blog.domain.skill.Skill;
import shop.mtcoding.blog.domain.skill.SkillJPARepository;

import shop.mtcoding.blog.domain.user.User;
import shop.mtcoding.blog.domain.user.UserJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobsService {
    private final JobsJPARepository jobsRepo;
    private final UserJPARepository userRepo;
    private final SkillJPARepository skillRepo;
    private final ResumeJPARepository resumeRepo;
    private final ApplyJPARepository applyRepo;


    public List<JobsResponse.IndexSearchDTO> searchKeyword(String keyword) {
        List<Jobs> jobsList = jobsRepo.findAllKeyword(keyword);
        List<JobsResponse.IndexSearchDTO> indexSearchDTOList = new ArrayList<>();

        System.out.println(keyword);
        System.out.println("jobsList size : " + jobsList.size());
        jobsList.stream().map(jobs -> {
            return indexSearchDTOList.add(JobsResponse.IndexSearchDTO.builder()
                    .jobs(jobs)
                    .user(jobs.getUser())
                    .skillList(jobs.getSkillList()).build());
                }).collect(Collectors.toList());

        return indexSearchDTOList;

    }

    @Transactional
    public JobsResponse.JobResumeDetailDTO jobsDetailDTO(Integer userJobsId, User sessionUser) {
        Jobs jobs = jobsRepo.findByIdJoinUserWithSkill(userJobsId);
        jobs.setIsOwner(jobs.getUser().getId() == sessionUser.getId());
        List<Resume> notApplyResumeList = resumeRepo.findAllDetailResumeByUserId(sessionUser.getId());  // 공고에 지원하지않은 이력서리스트
        List<Apply> applyList = applyRepo.findAllUserByApply(sessionUser.getId()); // 세션유저가 지원한 시청리스트


        JobsResponse.JobDetailDTO2.UserDTO user = new JobsResponse.JobDetailDTO2.UserDTO(jobs.getUser());
        List<JobsResponse.JobDetailDTO2.SkillDTO> skillList = jobs.getSkillList().stream().map(JobsResponse.JobDetailDTO2.SkillDTO::new).toList();
        JobsResponse.JobDetailDTO2 job = new JobsResponse.JobDetailDTO2(jobs, user, skillList);


        List<JobsResponse.NotResume> notResumeList = notApplyResumeList.stream().map(JobsResponse.NotResume::new).toList();


        for (int i = 0; i < notApplyResumeList.size(); i++) {
            boolean yetApply = false;

            if (applyRepo.findApplyByResumeId(notApplyResumeList.get(i).getId(), userJobsId) == null) {
                yetApply = true;
            }

            notResumeList.get(i).setIsyetApply(yetApply);
        }

        boolean isApply = false;

        if(notApplyResumeList.size() < 1 &&  applyList.size() > 1){
            isApply = true;
        }

        JobsResponse.ResumeDetailDTO resumeDetailDTO = new JobsResponse.ResumeDetailDTO(isApply, notResumeList);

        return new JobsResponse.JobResumeDetailDTO(job, resumeDetailDTO);
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
    public JobsResponse.JonsSaveDTO save(User sessionComp, JobsRequest.JobsSaveDTO reqDTO) {
        // 공고작성 하기
        Jobs jobs = jobsRepo.save(reqDTO.toEntity(reqDTO, sessionComp));

        return new JobsResponse.JonsSaveDTO(jobs, reqDTO.getSkillList());
    }

    public JobsResponse.writeJobsFormDTO writeJobsForm(User sessionComp) {

        return new JobsResponse.writeJobsFormDTO(sessionComp);
    }

    @Transactional
    public void delete(Integer id) {
        jobsRepo.deleteById(id);
    }

    @Transactional
    public JobsResponse.UpdateDTO update(Integer jobsId, JobsRequest.UpdateDTO reqDTO) {
        Jobs jobs = jobsRepo.findById(jobsId)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));

        jobs.setTitle(reqDTO.getTitle());
        jobs.setEdu(reqDTO.getEdu());
        jobs.setCareer(reqDTO.getCareer());
        jobs.setContent(reqDTO.getContent());
        jobs.setArea(reqDTO.getArea());
        jobs.setDeadline(reqDTO.getDeadLine());
        jobs.setTask(reqDTO.getTask());

        System.out.println(reqDTO.getArea());

        skillRepo.deleteByJobsId(jobsId);

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
                .skillChecked(new JobsResponse.JobUpdateDTO.SkillCheckedDTO(skillList))
                .build();

        return reqDTO;
    }
}

