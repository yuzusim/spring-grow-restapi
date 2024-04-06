package shop.mtcoding.blog.domain.comp;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.util.JwtUtil;
import shop.mtcoding.blog.domain.apply.Apply;
import shop.mtcoding.blog.domain.apply.ApplyJPARepository;
import shop.mtcoding.blog.domain.jobs.Jobs;
import shop.mtcoding.blog.domain.jobs.JobsJPARepository;
import shop.mtcoding.blog.domain.jobs.JobsResponse;
import shop.mtcoding.blog.domain.resume.Resume;
import shop.mtcoding.blog.domain.resume.ResumeJPARepository;
import shop.mtcoding.blog.domain.resume.ResumeResponse;
import shop.mtcoding.blog.domain.skill.Skill;
import shop.mtcoding.blog.domain.skill.SkillJPARepository;
import shop.mtcoding.blog.domain.user.User;
import shop.mtcoding.blog.domain.user.UserJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompService {
    private final UserJPARepository userJPARepo;
    private final CompJPARepository compJPARepo;
    private final ResumeJPARepository resumeJPARepo;
    private final SkillJPARepository skillJPARepo;
    private final JobsJPARepository jobsJPARepo;
    private final ApplyJPARepository applyJPARepo;


    //기업 로그인하면 보여줄 이력서 목록 (api 전환)
    public List<CompResponse.CompMainDTO> compMainList() {
        List<Resume> resumeList = resumeJPARepo.findAllMainList();

//        List<Resume> resumeList = resumeJPARepo.findAllJoinUser();
//        List<Skill> skillList = skillJPARepo.findAllResumeWithSkill();

        return resumeList.stream().map(resume ->
                new CompResponse.CompMainDTO(resume)).collect(Collectors.toList());

    }

    public List<ResumeResponse.CompManageDTO> findAllAppli(Integer userId) {
        List<Apply> applyList = applyJPARepo.findAllByUidJoinResumeJobsSkills(userId);

        List<ResumeResponse.CompManageDTO> respList = new ArrayList<>();

        applyList.stream().map(apply ->
                respList.add(ResumeResponse.CompManageDTO.builder()
                        .apply(apply)
                        .jobs(apply.getJobs())
                        .resume(apply.getResume())
                        .skillList(apply.getResume().getSkillList()).build())).toList();

        for (int i = 0; i < respList.size(); i++) {
            respList.get(i).setId(i + 1);
        }
        return respList;
    }

    public List<ResumeResponse.CompManageDTO> findNoResp(Integer userId) {
        List<Apply> applyList = applyJPARepo.findAllByUidI2(userId);
        List<ResumeResponse.CompManageDTO> respList = new ArrayList<>();

        applyList.stream().map(apply -> {
            return respList.add(ResumeResponse.CompManageDTO.builder()
                    .resume(apply.getResume())
                    .jobs(apply.getJobs())
                    .apply(apply)
                    .skillList(apply.getResume().getSkillList()).build());
        }).toList();

        for (int i = 0; i < respList.size(); i++) {
            respList.get(i).setId(i + 1);
        }
        return respList;

    }

    public CompResponse.CompManageDTO compManage(Integer userId) {
        Integer jobsCount = jobsJPARepo.countByUserId(userId);
        List<Apply> applicantList = applyJPARepo.findAllByUidN1(userId);
        List<Apply> noRespList = applyJPARepo.findAllByUidI2(userId);
        List<Jobs> jobsList = jobsJPARepo.findAllByUserIdWithSkill(userId);

        return new CompResponse.CompManageDTO(jobsCount, applicantList.size(), noRespList.size(), jobsList);
    }


    public List<CompResponse.RusaDTO> findApplicants(Integer jobsId) {
        List<Apply> applyList = applyJPARepo.findAllByJIdJoinRJoinJN1(jobsId);
        System.out.println("어플라이 갯수 : " + applyList.get(0).getResume().getArea());
        List<CompResponse.RusaDTO> respList = new ArrayList<>();

        respList = applyList.stream().map(apply -> CompResponse.RusaDTO.builder()
                .apply(apply)
                .resume(apply.getResume())
                .user(apply.getResume().getUser())
                .skills(apply.getResume().getSkillList()).build()).toList();
        for (int i = 0; i < respList.size(); i++) {
            respList.get(i).setId(i + 1);
        }
        return respList;
    }


    // 기업 회원가입
    @Transactional
    public User join(CompRequest.CompJoinDTO reqDTO) {
        // 회원가입 할 때마다 이미지 못가져와서 터지니까 디폴트 이미지 하나 추가함
        return compJPARepo.save(reqDTO.toEntity(2));
    }


    public List<JobsResponse.ApplyResumeListDTO> findAllByJobsId(Integer jobsId) {
        List<Apply> applyList = applyJPARepo.findAllByJobsId(jobsId);
        List<JobsResponse.ApplyResumeListDTO> listDTOS = new ArrayList<>();


        for (int i = 0; i < applyList.size(); i++) {
            List<Skill> skillList = skillJPARepo.findAllByJoinResumeId(applyList.get(i).getResume().getId());
            listDTOS.add(JobsResponse.ApplyResumeListDTO.builder()
                    .resume(applyList.get(i).getResume())
                    .myName(applyList.get(i).getResume().getUser().getMyName())
                    .jobs(applyList.get(i).getJobs())
                    .isPass(applyList.get(i).getIsPass())
                    .skills(skillList)
                    .build());
        }
        return listDTOS;
    }

    public List<CompResponse.CompHomeDTO> compHomeDTOS(Integer sessionUserId) {
        List<Jobs> jobsList = jobsJPARepo.findAllByUserIdWithSkill(sessionUserId);
        List<CompResponse.CompHomeDTO> respList = new ArrayList<>();

        jobsList.stream().map(jobs ->
                respList.add(CompResponse.CompHomeDTO.builder()
                        .jobs(jobs)
                        .skillList(jobs.getSkillList()).build())).toList();

        for (int i = 0; i < respList.size(); i++) {
            respList.get(i).setId(i + 1);
        }

        return respList;
    }

    //기업 로그인 시 보여줄 채용 공고
    public List<CompResponse.CompJobsInfoDTO> jobsInfoList() {
        List<Jobs> jobsList = jobsJPARepo.findAllJobsList();
        return jobsList.stream().map(jobs ->
                new CompResponse.CompJobsInfoDTO(jobs)).collect(Collectors.toList());
    }

    //기업 로그인하면 보여줄 채용 공고
//    public List<CompResponse.JobsSkillDTO> jobsList() {
//        List<Jobs> jobsList = jobsJPARepo.findAll();
//        List<CompResponse.JobsSkillDTO> jobsSkillList = new ArrayList<>();
//
//        for (int i = 0; i < jobsList.size(); i++) {
//            User user = userJPARepo.findById(jobsList.get(i).getUser().getId())
//                    .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));
//
//            List<Skill> skillList = skillJPARepo.findAllByJobsId(jobsList.get(i).getId());
//            jobsSkillList.add(CompResponse.JobsSkillDTO.builder()
//                    .jobs(jobsList.get(i))
//                    .user(user)
//                    .skillList(skillList)
//                    .build());
//        }
//        return jobsSkillList;
//
//    }

    // 기업 로그인하면 보여줄 이력서 목록들
    public List<CompResponse.ResumeUserSkillDTO> readResume() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Resume> resumeList = resumeJPARepo.findAllJoinUserWithSkills(sort);

        List<CompResponse.ResumeUserSkillDTO> rusList = new ArrayList<>();

        resumeList.stream().map(resume ->
                rusList.add(CompResponse.ResumeUserSkillDTO.builder()
                        .resume(resume)
                        .user(resume.getUser())
                        .skills(resume.getSkillList()).build())).toList();

        return rusList;
    }

    @Transactional
    public User updateById(Integer sessionUserId, CompRequest.UpdateDTO requestDTO) {
        User user = compJPARepo.findById(sessionUserId)
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));

        if (requestDTO.getMyName() != null) {
            user.setMyName(requestDTO.getMyName());
        }

        if (requestDTO.getPassword() != null) {
            user.setPassword(requestDTO.getPassword());
        }

        if (requestDTO.getCompName() != null) {
            user.setCompName(requestDTO.getCompName());
        }

        if (requestDTO.getPhone() != null) {
            user.setPhone(requestDTO.getPhone());
        }

        if (requestDTO.getHomepage() != null) {
            user.setHomepage(requestDTO.getHomepage());
        }

        if (requestDTO.getBirth() != null) {
            user.setBirth(requestDTO.getBirth());
        }

        if (requestDTO.getBusinessNumber() != null) {
            user.setBusinessNumber(requestDTO.getBusinessNumber());
        }

        if (requestDTO.getAddress() != null) {
            user.setAddress(requestDTO.getAddress());
        }

        return user;
    }

    //기업 유저 회원 정보 업데이트용 조회 (update-form)
    public CompResponse.CompUpdateDTO findByIdUpdate(Integer sessionUserId) {
        User user = compJPARepo.findById(sessionUserId)
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
        return new CompResponse.CompUpdateDTO(user);

    }


}
