package shop.mtcoding.blog.domain.user;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.domain.apply.Apply;
import shop.mtcoding.blog.domain.apply.ApplyJPARepository;
import shop.mtcoding.blog.domain.comp.CompRequest;
import shop.mtcoding.blog.domain.jobs.Jobs;
import shop.mtcoding.blog.domain.jobs.JobsJPARepository;
import shop.mtcoding.blog.domain.resume.Resume;
import shop.mtcoding.blog.domain.resume.ResumeJPARepository;
import shop.mtcoding.blog.domain.skill.Skill;
import shop.mtcoding.blog.domain.skill.SkillJPARepository;

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

    @Transactional
    public UserResponse.UpdatedCompDTO updateByCompId(User sessionUser, UserRequest.UpdateCompDTO reqDTO) {
        User user = userRepo.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
        user.updateComp(reqDTO);
        return new UserResponse.UpdatedCompDTO(user);
    }

    @Transactional
    public UserResponse.UserUpdateDTO updateByUserId(User sessionUser, UserRequest.UpdateUserDTO reqDTO) {
        User user = userRepo.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
        user.updateUser(reqDTO);
        return new UserResponse.UserUpdateDTO(user);
    }

    @Transactional
    public UserResponse.UserHomeDTO userHome(Integer userId) {
        Integer waitCount = userRepo.findByUserIdN1(userId);
        Integer resultCount = userRepo.findByUserId34(userId);
        Integer applyCount = userRepo.findAllbyUserId(userId);

        List<Resume> resumeList = resumeRepo.findAllByUserId(userId);

        UserResponse.UserHomeDTO userHomeDTO =
                new UserResponse.UserHomeDTO(applyCount, waitCount, resultCount, resumeList);

        return userHomeDTO;
    }


    public List<UserResponse.FindJobsResumeDTO> findJobsResumeDTOS(Integer resumeId) {
        List<Apply> applyList = applyRepo.findAppliesByNot1ByResumeId(resumeId);

        Resume resume = resumeRepo.findById(resumeId)
                .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));

        List<UserResponse.FindJobsResumeDTO> ursDTOList = applyList.stream()
                .map(apply -> {
                    Jobs jobs = jobsRepo.findById(apply.getJobs().getId())
                            .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));

                    User compUser = userRepo.findById(jobs.getUser().getId())
                            .orElseThrow(() -> new Exception404(" 사용자를 찾을 수 없습니다."));

                    List<Skill> skills = skillRepo.findAllByJobsId(apply.getJobs().getId());

                    return UserResponse.FindJobsResumeDTO.builder()
                            .user(compUser)
                            .jobs(jobs)
                            .apply(apply)
                            .resume(resume)
                            .skillList(skills).build();
                }).collect(Collectors.toList());

        for (int i = 0; i < ursDTOList.size(); i++) {
            ursDTOList.get(i).setId(i + 1);
        }
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


    //user 회원가입 메소드
    @Transactional
    public UserResponse.UserJoinDTO join(UserRequest.JoinDTO reqDTO, Integer role) {
        User user = userRepo.save(reqDTO.toEntity(role));
        return new UserResponse.UserJoinDTO(user);

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
    //유저 회원 정보 업데이트용 조회
    public User findById(Integer sessionUserId) {
        User user = userRepo.findById(sessionUserId)
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
        return user;

    }

    public UserResponse.UpdateUserFormDTO updateUserForm(Integer sessionUserId) {
        User user = userRepo.findById(sessionUserId)
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
        return new UserResponse.UpdateUserFormDTO(user);
    }


    public UserResponse.UpdateCompFormDTO updateCompForm(Integer sessionUserId) {
        User user = userRepo.findById(sessionUserId)
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
        return new UserResponse.UpdateCompFormDTO(user);
    }
}