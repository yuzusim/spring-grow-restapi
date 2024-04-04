package shop.mtcoding.blog.model.comp;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.apply.ApplyJPARepository;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsJPARepository;
import shop.mtcoding.blog.model.jobs.JobsResponse;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserJPARepository;
import shop.mtcoding.blog.model.user.UserResponse;

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

    public List<ResumeResponse.CmrDTO> findAllAppli(Integer userId) {
        List<Apply> applyList = applyJPARepo.findAllByUidN1(userId);

        List<ResumeResponse.CmrDTO> cmrDTOList = new ArrayList<>();
        applyList.stream().map(apply -> {
            return cmrDTOList.add(ResumeResponse.CmrDTO.builder()
                    .resume(apply.getResume())
                    .apply(apply)
                    .skillList(apply.getResume().getSkillList()).build());
        }).collect(Collectors.toList());
        for (int i = 0; i < cmrDTOList.size(); i++) {
            cmrDTOList.get(i).setId(i + 1);
        }
        return cmrDTOList;
    }

    public List<ResumeResponse.CmrDTO> findNoResp(Integer userId) {
        List<Apply> applyList = applyJPARepo.findAllByUidI2(userId);

        List<ResumeResponse.CmrDTO> cmrDTOList = new ArrayList<>();

        applyList.stream().map(apply -> {
            return cmrDTOList.add(ResumeResponse.CmrDTO.builder()
                    .resume(apply.getResume())
                    .apply(apply)
                    .skillList(apply.getResume().getSkillList()).build());
        }).collect(Collectors.toList());
        for (int i = 0; i < cmrDTOList.size(); i++) {
            cmrDTOList.get(i).setId(i + 1);
        }

        return cmrDTOList;

    }

    public CompResponse.CompManageDTO compManage(Integer userId) {
        Integer jobsCount = jobsJPARepo.countByUserId(userId);
        List<Apply> applicantList = applyJPARepo.findAllByUidN1(userId);
        List<Apply> noRespList = applyJPARepo.findAllByUidI2(userId);
        List<Jobs> jobsList = jobsJPARepo.findAllByUserIdWithSkill(userId);

        return new CompResponse.CompManageDTO(jobsCount, applicantList.size(), noRespList.size(), jobsList);
    }


    public List<CompResponse.RusaDTO> findApplicants(Integer jobsId) {
        //공고에 지원한 이력서를 전부 찾는데, 그 중 지원안한 상태는 제외해서 조회
        List<Apply> applyList = applyJPARepo.findAllByJidAn1(jobsId);
        // DTO를 받을 그릇 준비
        List<CompResponse.RusaDTO> rusaDTOList = new ArrayList<>();

        /*
         * 원래 for문으로 조회해서 주입하였으나 그 방식은 서버에 부담이 크다.
         * 쿼리를 for문으로 날린다는게...
         */
        applyList.stream().map(apply -> {
            // apply에 Resume가 매핑되어있어서 가져옴
            Resume resume = apply.getResume();
            /*
             * 근데 apply안에 resume안에 user는 잘 가져올수있을까??
             * 그래서 sout찍어보기 테스트
             */
            User user = apply.getResume().getUser();
            System.out.println(resume.getUser().toString());

            return rusaDTOList.add(CompResponse.RusaDTO.builder()
                    .user(user).resume(resume).apply(apply).build());
        }).collect(Collectors.toList());

        for (int i = 0; i < rusaDTOList.size(); i++) {
            rusaDTOList.get(i).setId(i + 1);
        }
        return rusaDTOList;
    }


    // 기업 회원가입
    @Transactional
    public CompResponse.CompJoinDTO join(CompRequest.CompJoinDTO reqDTO) {

        // 회원가입 할 때마다 이미지 못가져와서 터지니까 디폴트 이미지 하나 추가함
        User comp = compJPARepo.save(reqDTO.toEntity(2));

//        // 전에거에 있던 이메일 찾아서 그걸로 세션저장해서 회원가입 직후 바로 로그인 되는거 구현하려고 만듬
//        compJPARepo.findByEmail(reqDTO.getEmail());

        return new CompResponse.CompJoinDTO(comp);
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

    public List<CompResponse.CompHomeDTO> findAllByUserId(User sessionComp) {
        List<Jobs> jobsList = jobsJPARepo.findAllByUserId(sessionComp.getId());
        List<CompResponse.CompHomeDTO> listDTOS = new ArrayList<>();

        for (int i = 0; i < jobsList.size(); i++) {
            List<Skill> skills = skillJPARepo.findByJobsId(jobsList.get(i).getId());
            listDTOS.add(CompResponse.CompHomeDTO.builder()
                    .jobs(jobsList.get(i))
                    .skillList(skills)
                    .build());
        }

        for (int i = 0; i < listDTOS.size(); i++) {
            listDTOS.get(i).setId(i + 1);
        }

        return listDTOS;
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
    public List<CompResponse.ResumeUserSkillDTO> findAllRusList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        List<Resume> resumeList = resumeJPARepo.findAll(sort);

        List<CompResponse.ResumeUserSkillDTO> rusList = new ArrayList<>();

        for (int i = 0; i < resumeList.size(); i++) {
            User user = userJPARepo.findById(resumeList.get(i).getUser().getId())
                    .orElseThrow(() -> new Exception401("사용자를 찾을 수 없습니다."));
            List<Skill> skills = skillJPARepo.findAllByResumeId(resumeList.get(i).getId());
            rusList.add(CompResponse.ResumeUserSkillDTO.builder()
                    .resume(resumeList.get(i))
                    .user(user)
                    .skills(skills)
                    .build());
        }

        return rusList;
    }

    @Transactional
    public User updateById(User sessionUser, CompRequest.UpdateDTO requestDTO) {
        User user = compJPARepo.findById(sessionUser.getId())
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
