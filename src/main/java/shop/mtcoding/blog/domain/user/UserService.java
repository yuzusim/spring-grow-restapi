package shop.mtcoding.blog.domain.user;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.domain.apply.Apply;
import shop.mtcoding.blog.domain.apply.ApplyJPARepository;
import shop.mtcoding.blog.domain.resume.Resume;
import shop.mtcoding.blog.domain.resume.ResumeJPARepository;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userRepo;
    private final ResumeJPARepository resumeRepo;
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
    public UserResponse.HomeDTO userHome(Integer userId) {
        Integer waitCount = userRepo.findByUserIdN1(userId);
        Integer resultCount = userRepo.findByUserId34(userId);
        Integer applyCount = userRepo.findAllbyUserId(userId);

        List<Resume> resumeList = resumeRepo.findAllByUserId(userId);

        return new UserResponse.HomeDTO(applyCount, waitCount, resultCount, resumeList);
    }


    public List<UserResponse.FindJobsResumeDTO> findJobsResumeDTOS(Integer resumeId) {
        List<Apply> applyList = applyRepo.findAppliesByNot1ByResumeId(resumeId);
        List<UserResponse.FindJobsResumeDTO> respList = new ArrayList<>();

        applyList.stream().map(apply ->
                respList.add(UserResponse.FindJobsResumeDTO.builder()
                        .user(apply.getResume().getUser())
                        .jobs(apply.getJobs())
                        .apply(apply)
                        .resume(apply.getResume())
                        .skillList(apply.getJobs().getSkillList())
                        .build())).toList();

        for (int i = 0; i < respList.size(); i++) {
            respList.get(i).setId(i + 1);
        }
        return respList;
    }

    //사용자 정보와 이력서에 들어간 스킬을 구해다 주는 DTO
    public List<UserResponse.resumeHomeDTO> resumeHome(Integer userId) {
        List<Resume> resumeList = resumeRepo.findAllByUserId(userId);
        List<UserResponse.resumeHomeDTO> respList = new ArrayList<>();

        resumeList.stream().map(resume ->
                respList.add(UserResponse.resumeHomeDTO.builder()
                        .user(resume.getUser())
                        .resume(resume)
                        .skillList(resume.getSkillList()).build())).toList();
        for (int i = 0; i < respList.size(); i++) {
            respList.get(i).setId(i + 1);
        }
        return respList;
    }


    // user 회원가입 메소드
    @Transactional
    public User join(UserRequest.JoinDTO reqDTO, Integer role) {
        return userRepo.save(reqDTO.toEntity(role));
    }

    // 로그인
    public User login(UserRequest.LoginDTO reqDTO) {
        try {
            return userRepo.findByIdAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                    .orElseThrow(() -> new Exception404("회원 정보가 없습니다."));
        } catch (EmptyResultDataAccessException e) {
            throw new Exception401("아이디,비밀번호가 틀렸어요");
        }
    }

    // 가입 email 중복 조회
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    // 유저 회원 정보 업데이트용 조회
    public User findById(Integer sessionUserId) {
        return userRepo.findById(sessionUserId)
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
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