package shop.mtcoding.blog.model.resume;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.apply.ApplyJPARepository;
import shop.mtcoding.blog.model.apply.ApplyResponse;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;
import shop.mtcoding.blog.model.skill.SkillResponse;
import shop.mtcoding.blog.model.user.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepo;
    private final ApplyJPARepository applyJPARepo;
    private final SkillJPARepository skillJPARepo;
    private final SkillJPARepository skillRepo;
    private final HttpSession session;

    //이력서 상세보기
    public ResumeResponse.DetailDTO resumeDetail(Integer resumeId, Integer jobsId, User sessionUser, User sessionComp) {
        Resume resume = resumeJPARepo.findByIdJoinUser(resumeId);

        boolean isOwner = resume.getUser().equals(sessionUser);
        resume.setOwner(isOwner);

        List<Skill> skills = skillJPARepo.findAllByResumeId(resume.getId());
        Apply apply = applyJPARepo.findByResumeIdAndJobsId(resumeId, jobsId)
                .orElseThrow(() -> new Exception400("잘못된 요청입니다."));
        if (sessionUser != null) {
            ResumeResponse.DetailDTO resumeDetailDTO = new ResumeResponse.DetailDTO(resume, jobsId, apply.getIsPass(), resume.getUser(), sessionUser.getRole(), skills);

            return resumeDetailDTO;
        } else if (sessionComp != null) {
            ResumeResponse.DetailDTO resumeDetailDTO = new ResumeResponse.DetailDTO(resume, jobsId, apply.getIsPass(), resume.getUser(), sessionComp.getRole(), skills);

            return resumeDetailDTO;
        }

        return null;
    }

    public ResumeResponse.DetailDTO2 resumeDetail2(Integer resumeId, User sessionUser) {
        Resume resume = resumeJPARepo.findByIdJoinUser(resumeId);
        boolean isOwner = resume.getUser().equals(sessionUser);
        resume.setOwner(isOwner);

        List<Skill> skills = skillJPARepo.findAllByResumeId(resume.getId());


        ResumeResponse.DetailDTO2 resumeDetailDTO = ResumeResponse.DetailDTO2.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .edu(resume.getEdu())
                .introduce(resume.getIntroduce())
                .imgFileName(resume.getUser().getImgFileName())
                .myName(resume.getUser().getMyName())
                .birth(resume.getUser().getBirth())
                .phone(resume.getUser().getPhone())
                .email(resume.getUser().getEmail())
                .address(resume.getUser().getAddress())
                .area(resume.getArea())
                .career(resume.getCareer())
                .portLink(resume.getPortLink())
                .userId(resume.getUser().getId())
                .skills(skills)
                .build();


        return resumeDetailDTO;
    }

    public ResumeResponse.CompDetailDTO2 CompResumeDetail2(Integer resumeId, User sessionUser) {
        Resume resume = resumeJPARepo.findByIdJoinUser(resumeId);
        boolean isOwner = resume.getUser().equals(sessionUser);
        resume.setOwner(isOwner);

        List<Skill> skills = skillJPARepo.findAllByResumeId(resume.getId());


        ResumeResponse.CompDetailDTO2 resumeDetailDTO = ResumeResponse.CompDetailDTO2.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .edu(resume.getEdu())
                .introduce(resume.getIntroduce())
                .imgFileName(resume.getUser().getImgFileName())
                .myName(resume.getUser().getMyName())
                .birth(resume.getUser().getBirth())
                .phone(resume.getUser().getPhone())
                .email(resume.getUser().getEmail())
                .address(resume.getUser().getAddress())
                .area(resume.getArea())
                .career(resume.getCareer())
                .portLink(resume.getPortLink())
                .userId(resume.getUser().getId())
                .skills(skills)
                .build();


        return resumeDetailDTO;
    }


    public ResumeResponse.ResumeStateDTO findAllResumeJoinApplyByUserIdAndJobsId(Integer userId, Integer jobsId) {
        List<Resume> resumeList = resumeJPARepo.findAllByUserId(userId);
        List<Apply> applies = applyJPARepo.findAll();

        //sessionUser 의 지원한 공고 리스트
        List<ApplyResponse.ApplyUserViewDTO> listDTO = applies.stream()
                .filter(apply -> apply.getResume().getUser().getId() == sessionUserId)
                .map(apply -> ApplyResponse.ApplyUserViewDTO.builder()
                        .id(apply.getId())
                        .user(apply.getResume().getUser())
                        .isPass(apply.getIsPass())
                        .resume(apply.getResume())
                        .jobs(apply.getJobs())
                        .build())
                .collect(Collectors.toList());


        // 내가 지원안한 이력서만 나오도록 출력
        List<ResumeResponse.ResumeApplyDTO> resumeApplyDTOList = resumeList.stream()
                .map(resume -> {
                    Apply apply = applyJPARepo.findByResumeIdAndJobsId(resume.getId(), jobsId)
                            .orElse(Apply.builder().isPass("1").build());

                    if ("1".equals(apply.getIsPass())) {
                        return ResumeResponse.ResumeApplyDTO.builder()
                                .apply(apply)
                                .resume(resume)
                                .build();
                    }
                    return null;
                })
                .filter(Objects::nonNull) // 필터링하여 null이 아닌 요소만 남깁니다.
                .collect(Collectors.toList());

        // 지원할 이력서가 있으면 isApply false
        Boolean isApply = false;

        //지원한 이력서가 있고 작성한이력서 리스트가 비었으면 isApply true
        if (resumeApplyDTOList.size() < 1 && listDTO.size() > 1) {
            isApply = true;
        }

        ResumeResponse.ResumeStateDTO resumeStateDTO = new ResumeResponse.ResumeStateDTO();

        resumeStateDTO.setIsApply(isApply);
        resumeStateDTO.setApplys(resumeApplyDTOList);


        return resumeStateDTO;
    }

    //이력서 뿌리기
    public List<ResumeResponse.ResumeDTO> findAll() {
        List<Resume> resumes = resumeJPARepo.findAll();

        return resumes.stream().map(resume -> resume.toDTO()).collect(Collectors.toList());
    }

    // 이력서 수정
    public ResumeResponse.UpdateDTO updateForm(Integer id) {
        Resume resume = resumeJPARepo.findById(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        List<Skill> skill = skillRepo.findAllByResumeId(id);
        ResumeResponse.UpdateDTO reqDTO = ResumeResponse.UpdateDTO.builder()
                .title(resume.getTitle())
                .area(resume.getArea())
                .edu(resume.getEdu())
                .career(resume.getCareer())
                .introduce(resume.getIntroduce())
                .portLink(resume.getPortLink())
                .skillChecked(new SkillResponse.SkillCheckedDTO(skill)).build();
        return reqDTO;
    }


    @Transactional
    public ResumeResponse.ResumeUpdateDTO update(Integer id, Integer sessionUserId, ResumeRequest.UpdateDTO reqDTO) {
        // 1. 조회 및 예외처리
        // 주어진 resumeId로 이력서를 찾습니다.
        Resume resume = resumeJPARepo.findById(id)
                .orElseThrow(() -> new Exception404("해당 이력서를 찾을 수 없습니다"));
        // 2. 권한 처리
        if (sessionUserId != resume.getUser().getId()) {
            throw new Exception403("이력서를 수정할 권한이 없습니다");
        }

        // 3. 이력서 수정하기
        resume.setId(reqDTO.getId());
        resume.setTitle(reqDTO.getTitle());
        resume.setArea(reqDTO.getArea());
        resume.setEdu(reqDTO.getEdu());
        resume.setCareer(reqDTO.getCareer());
        resume.setIntroduce(reqDTO.getIntroduce());
        resume.setPortLink(reqDTO.getPortLink());


        skillRepo.deleteByresumeId(id);

        // 스킬뿌리기
        reqDTO.getSkill().stream()
                .map((skill) -> {
                    return skill.toEntity(resume);
                })
                .forEach((skill) -> {
                    skillRepo.save(skill);
                });


        System.out.println("수정된 데이터 : " + reqDTO);

        List<Skill> skills = skillJPARepo.findByResumeId(resume.getId());

        return new ResumeResponse.ResumeUpdateDTO(resume, skills);

    } // 더티체킹


    //이력서 신청
    @Transactional
    public void save(ResumeRequest.SaveDTO saveDTO) {
        //1. 인증처리 : 유저가 세션을가지고있는지 로그인상태 확인
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new Exception401("로그인이 필요한 서비스입니다");
        }

        //2. 이력서 작성
        Resume resume = saveDTO.toEntity(sessionUser);
        Resume savedResume = resumeJPARepo.save(resume);
        System.out.println("------------------" + resume.getId());

        // 3. 스킬 작성
        saveDTO.getSkillList().stream()
                .map((skill) -> {
                    return skill.toEntity(savedResume);
                })
                .forEach((skill) -> {
                    skillJPARepo.save(skill);
                });
    }

    //이력서 삭제
    @Transactional
    public void delete(Integer boardId) {
        //1. 인증처리
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser.getId() == null) {
            throw new Exception401("서비스가 필요한 서비스입니다.");
        }
        Resume resume = resumeJPARepo.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다"));
        //2.권한처리
        if (sessionUser.getId() != resume.getUser().getId()) {
            throw new Exception403("해당 게시글을 삭제할 권한이 없습니다");
        }

        //3. 삭제하기
        resumeJPARepo.deleteById(resume.getId());
    }

}
