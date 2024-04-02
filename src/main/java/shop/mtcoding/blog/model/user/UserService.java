package shop.mtcoding.blog.model.user;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.apply.ApplyJPARepository;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsJPARepository;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;
import shop.mtcoding.blog.model.resume.ResumeRequest;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userRepo;
    private final ResumeJPARepository resumeRepo;
    private final SkillJPARepository skillRepo;
    private final JobsJPARepository jobsRepo;
    private final ApplyJPARepository applyRepo;

    public UserResponse.UserHomeDTO userHome(Integer userId) {
        Integer waitCount = userRepo.findByUserIdN1(userId);
        Integer resultCount = userRepo.findByUserId34(userId);
        Integer applyCount = userRepo.findAllbyUserId(userId);

        List<Resume> resumeList = resumeRepo.findAllByUserId(userId);

        UserResponse.UserHomeDTO userHomeDTO =
                new UserResponse.UserHomeDTO(applyCount, waitCount, resultCount, resumeList);

        return userHomeDTO;
    }


    public List<UserResponse.UrsDTO> ursDTOS(Integer resumeId) {
        List<Apply> applyList = applyRepo.findAppliesByNot1ByResumeId(resumeId);

        Resume resume = resumeRepo.findById(resumeId)
                .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));

        List<UserResponse.UrsDTO> ursDTOList = applyList.stream()
                .map(apply -> {
                    Jobs jobs = jobsRepo.findById(apply.getJobs().getId())
                            .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));

                    User compUser = userRepo.findById(jobs.getUser().getId())
                            .orElseThrow(() -> new Exception404(" 사용자를 찾을 수 없습니다."));

                    List<Skill> skills = skillRepo.findAllByJobsId(apply.getJobs().getId());

                    return UserResponse.UrsDTO.builder()
                            .user(compUser)
                            .jobs(jobs)
                            .apply(apply)
                            .resume(resume)
                            .skillList(skills).build();
                }).collect(Collectors.toList());

        return ursDTOList;

    }

    //사용자 정보와 이력서에 들어간 스킬을 구해다 주는 DTO
    public List<UserResponse.UserResumeSkillDTO> userResumeSkillDTO(Integer userId) {

        List<UserResponse.UserResumeSkillDTO> ursList = new ArrayList<>();

        List<Resume> resumeList = resumeRepo.findAllByUserId(userId);

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new Exception401("사용자를 찾을 수 없습니다."));

        for (int i = 0; i < resumeList.size(); i++) {
            List<Skill> skills = skillRepo.findAllByResumeId(resumeList.get(i).getId());
            ursList.add(UserResponse.UserResumeSkillDTO.builder()
                    .user(user)
                    .resume(resumeList.get(i))
                    .skillList(skills).build());
        }
        return ursList;
    }


    @Transactional
    public User join(UserRequest.JoinDTO reqDTO, Integer role) {
        return userRepo.save(reqDTO.toEntity(role));
    }


    public User login(UserRequest.LoginDTO reqDTO) {
        try {
            return userRepo.findByIdAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                    .orElseThrow(() -> new Exception401("회원 정보가 없습니다."));
        } catch (EmptyResultDataAccessException e) {
            throw new Exception401("아이디,비밀번호가 틀렸어요");
        }
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }


    //유저 홈 리스트

//    public List<ResumeRequest.UserViewDTO> userHome(Integer sessionUserId) {
//        List<Resume> resumeList = resumeRepo.findAll();
//        User sessionUser = userRepo.findById(sessionUserId)
//                .orElseThrow(() -> new Exception404("사용자 정보를 찾을 수 없습니다."));
//        List<ResumeRequest.UserViewDTO> listDTO = resumeList.stream()
//                .filter(resume -> resume.getUser().getId() == sessionUser.getId()) // Filter resumes by ID = 1
//                .map(resume -> ResumeRequest.UserViewDTO.builder()
//                        .resume(resume)
//                        .skills(resume.getSkillList())
//                        .build())
//                .collect(Collectors.toList());
//
//        return listDTO;
//    }


    //유저 회원정보 폼 업데이트 메소드
    @Transactional
    public User updateById(User sessionUser, CompRequest.UpdateDTO requestDTO) {
        System.out.println(requestDTO);
        User user = userRepo.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
        user.update(requestDTO);
        return user;
    }

    //유저 회원 정보 업데이트용 조회
    public User findById(Integer sessionUserId) {
        User user = userRepo.findById(sessionUserId)
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
        return user;

    }


}