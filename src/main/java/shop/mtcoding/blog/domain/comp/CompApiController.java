package shop.mtcoding.blog.domain.comp;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog._core.util.JwtUtil;
import shop.mtcoding.blog._core.util.JwtVO;
import shop.mtcoding.blog.domain.resume.ResumeResponse;
import shop.mtcoding.blog.domain.resume.ResumeService;
import shop.mtcoding.blog.domain.user.SessionUser;
import shop.mtcoding.blog.domain.user.User;
import shop.mtcoding.blog.domain.user.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CompApiController {
    private final CompService compService;
    private final HttpSession session;
    private final UserService userService;
    private final ResumeService resumeService;

    // 기업 회원 가입
    @PostMapping("/comp/join")
    public ResponseEntity<?> compJoin(@Valid @RequestBody CompRequest.CompJoinDTO reqDTO, Errors errors) {
        User user = compService.join(reqDTO);
        String jwt = JwtUtil.create(user);
        return ResponseEntity.ok()
                .header(JwtVO.HEADER,JwtVO.PREFIX + jwt)
                .body(new ApiUtil<>(new CompResponse.CompJoinDTO(user)));
    }

    //기업 채용정보 (공고 뿌리기)
    @GetMapping("/api/comp/jobs-info")
    public ResponseEntity<?> jobsInfo() {
        List<CompResponse.CompJobsInfoDTO> respDTO = compService.jobsInfoList();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //기업 메인 (메인이라 api 안 붙였는데 필요하면 붙이세용)
    @GetMapping("/comps/comp-index")
    public ResponseEntity<?> compIndex() {
        List<CompResponse.CompMainDTO> reqsDTO = compService.compMainList();
        return ResponseEntity.ok(new ApiUtil<>(reqsDTO));
    }

    // 기업이 지원받은 이력서 상세보기 페이지 요청
    @PostMapping("/api/comp/resume-detail/{resumeId}")
    public ResponseEntity<?> resumeDetail(@PathVariable Integer resumeId, @RequestBody CompRequest.JobsIdDTO reqDTO) {
        SessionUser sessionComp = (SessionUser) session.getAttribute("sessionComp");
        User user = userService.findById(sessionComp.getId());
        ResumeResponse.CompResumeDetailDTO respDTO =
                resumeService.compResumeDetail(resumeId, reqDTO.getJobsId(), user);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 기업 사용자 공고 관리 페이지 요청
    @GetMapping("/api/comps/comp-home")
    public ResponseEntity<?> compHome() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionComp");
        System.out.println(sessionUser.getId());
        List<CompResponse.CompHomeDTO> respList = compService.compHomeDTOS(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respList));
    }





    // 기업 공개 이력서 열람 페이지 요청
    @GetMapping("/api/comps/read-resume")
    public ResponseEntity<?> readResume() {
        List<CompResponse.ResumeUserSkillDTO> rusList = compService.readResume();
        return ResponseEntity.ok(new ApiUtil<>(rusList));
    }




    //update-form
    @GetMapping("/api/comps/{id}")
    public ResponseEntity<?> updateForm(@PathVariable int id) {
        SessionUser sessionComp = (SessionUser) session.getAttribute("sessionComp");
        CompResponse.CompUpdateDTO respDTO = compService.findByIdUpdate(sessionComp.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/comps/comp-manage")
    public ResponseEntity<?> compManage() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        CompResponse.CompManageDTO compManageDTO = compService.compManage(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(compManageDTO));
    }

    @PostMapping("/api/find-all-jobs")
    public CompResponse.CompManageDTO compManageDTO(@RequestParam(name = "userId") Integer userId) {
        return compService.compManage(userId);
    }

    @PostMapping("/api/find-all-applicants")
    public List<ResumeResponse.CompManageDTO> findAllApplicants(@RequestParam(name = "userId") Integer userId) {
        return compService.findAllAppli(userId);
    }

    @PostMapping("/api/find-applicants")
    public List<CompResponse.RusaDTO> findApplicants(@RequestParam(name = "jobsId") Integer jId) {
        return compService.findApplicants(jId);
    }

    @PostMapping("/api/find-no-resp")
    public List<ResumeResponse.CompManageDTO> findNoResp(@RequestParam(name = "userId") Integer uId) {
        return compService.findNoResp(uId);
    }

}

