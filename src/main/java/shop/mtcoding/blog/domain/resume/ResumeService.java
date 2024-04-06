package shop.mtcoding.blog.domain.resume;

import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.domain.apply.Apply;
import shop.mtcoding.blog.domain.apply.ApplyJPARepository;
import shop.mtcoding.blog.domain.skill.Skill;
import shop.mtcoding.blog.domain.skill.SkillJPARepository;
import shop.mtcoding.blog.domain.user.SessionUser;
import shop.mtcoding.blog.domain.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeRepo;
    private final ApplyJPARepository applyRepo;
    private final SkillJPARepository skillRepo;

    public ResumeResponse.CompResumeDetailDTO compResumeDetail(Integer resumeId, Integer jobsId, User sessionUser) {
        Apply apply = applyRepo.findByRIdJIdUserSkills(resumeId, jobsId)
                .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));

        boolean isOwner = apply.getResume().getUser().equals(sessionUser);
        apply.getResume().setOwner(isOwner);

        return ResumeResponse.CompResumeDetailDTO.builder()
                .resume(apply.getResume())
                .user(apply.getResume().getUser())
                .apply(apply)
                .skills(apply.getResume().getSkillList()).build();
    }


    // 이력서 수정
    public ResumeResponse.UpdateDTO updateForm(Integer resumeId) {
        Resume resume = resumeRepo.findByIdWithSkills(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        return ResumeResponse.UpdateDTO.builder()
                .resume(resume)
                .skills(resume.getSkillList())
                .build();
    }

    // 개인 이력서 상세보기 페이지
    public ResumeResponse.DetailDTO resumeDetail(Integer resumeId, User sessionUser) {
        Resume resume = resumeRepo.findByResumeIdJoinUserWithSkills(resumeId);
        boolean isOwner = resume.getUser().equals(sessionUser);

        return ResumeResponse.DetailDTO.builder()
                .resume(resume)
                .user(resume.getUser())
                .skills(resume.getSkillList())
                .isOwner(isOwner)
                .build();
    }


    //이력서 상세보기 -- 로직 변환중 sessionComp없어도 될거 같아서
//    public ResumeResponse.DetailDTO2 resumeDetail(Integer resumeId, Integer jobsId, User sessionUser, User sessionComp) {
//        Resume resume = resumeRepo.findByIdJoinUser(resumeId);
//
//        boolean isOwner = resume.getUser().equals(sessionUser);
//        resume.setOwner(isOwner);
//
//        List<Skill> skills = skillRepo.findAllByResumeId(resume.getId());
//        Apply apply = applyRepo.findByResumeIdAndJobsId(resumeId, jobsId)
//                .orElseThrow(() -> new Exception400("잘못된 요청입니다."));
//        if (sessionUser != null) {
//            ResumeResponse.DetailDTO2 resumeDetailDTO = new ResumeResponse.DetailDTO(resume, jobsId, apply.getIsPass(), resume.getUser(), sessionUser.getRole(), skills);
//
//            return resumeDetailDTO;
//        } else if (sessionComp != null) {
//            ResumeResponse.DetailDTO2 resumeDetailDTO = new ResumeResponse.DetailDTO(resume, jobsId, apply.getIsPass(), resume.getUser(), sessionComp.getRole(), skills);
//
//            return resumeDetailDTO;
//        }
//
//        return null;
//    }

    public ResumeResponse.DetailDTO2 resumeDetail2(Integer resumeId, User sessionUser) {
        Resume resume = resumeRepo.findByIdJoinUser(resumeId);
        boolean isOwner = resume.getUser().equals(sessionUser);
        resume.setOwner(isOwner);

        List<Skill> skills = skillRepo.findAllByResumeId(resume.getId());


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
        Resume resume = resumeRepo.findByIdJoinUser(resumeId);
        boolean isOwner = resume.getUser().equals(sessionUser);
        resume.setOwner(isOwner);

        List<Skill> skills = skillRepo.findAllByResumeId(resume.getId());


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


    @Transactional
    public ResumeResponse.UpdatedDTO update(Integer resumeId, Integer sessionUserId, ResumeRequest.UpdateDTO reqDTO) {
        Resume resume = resumeRepo.findByResumeIdJoinUserWithSkills(resumeId);
        if (sessionUserId != resume.getUser().getId()) {
            throw new Exception403("권한이 없습니다");
        }
        resume.updateResume(reqDTO);
        return new ResumeResponse.UpdatedDTO(resume);

    } // 더티체킹


    //이력서 저장
    @Transactional
    public ResumeResponse.SaveDTO save(User sessionUser, ResumeRequest.SaveDTO reqDTO) {
        Resume resume = reqDTO.toEntity(sessionUser);
        Resume savedResume = resumeRepo.save(resume);
        for (Skill skill : reqDTO.getSkillList()) {
            skillRepo.save(Skill.builder()
                    .resume(savedResume)
                    .name(skill.getName())
                    .build());
        }

        return new ResumeResponse.SaveDTO(savedResume);
    }

    // 이력서 삭제
    @Transactional
    public void delete(Integer resumeId, SessionUser sessionUser) {
        if (sessionUser.getId() == null) {
            throw new Exception401("로그인이 필요한 서비스입니다.");
        }
        Resume resume = resumeRepo.findById(resumeId)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다"));

        if (sessionUser.getId() != resume.getUser().getId()) {
            throw new Exception403("해당 게시글을 삭제할 권한이 없습니다");
        }

        resumeRepo.deleteById(resumeId);
    }


}
