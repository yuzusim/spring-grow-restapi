package shop.mtcoding.blog.domain.comp;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.domain.resume.ResumeResponse;
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

    @PostMapping("/comp/join")
    public ResponseEntity<?> compJoin(@RequestBody CompRequest.CompJoinDTO reqDTO) {
        CompResponse.CompJoinDTO respDTO = compService.join(reqDTO);
        session.setAttribute("sessionComp", respDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //기업 채용정보 (공고 뿌리기)
    @GetMapping("/api/comp/jobs-info")
    public ResponseEntity<?> jobsInfo() {
        List<CompResponse.CompJobsInfoDTO> respDTO = compService.jobsInfoList();
//        request.setAttribute("jobsList", jobsList);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //기업 메인 (메인이라 api 안 붙였는데 필요하면 붙이세용)
    @GetMapping("/comps/comp-index")
    public ResponseEntity<?> compIndex() {
        List<CompResponse.CompMainDTO> reqsDTO = compService.compMainList();
//        request.setAttribute("rusList", rusList);
        return ResponseEntity.ok(new ApiUtil<>(reqsDTO));
    }

    @GetMapping("/api/comps/{id}/comp-home")
    public ResponseEntity<?> compHome(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionComp");
        User sessionComp = userService.findById(sessionUser.getId());
        List<CompResponse.ComphomeDTO> comphomeDTOList = compService.findAllByUserId(sessionComp);

        return ResponseEntity.ok(new ApiUtil<>(comphomeDTOList));
    }

    @GetMapping("/api/comps/read-resume")
    public ResponseEntity<?> readResume(HttpServletRequest request) {
        List<CompResponse.ResumeUserSkillDTO> rusList = compService.findAllRusList();
        request.setAttribute("rusList", rusList);
        return ResponseEntity.ok(new ApiUtil<>(rusList));
    }

    //update
    @PutMapping("/api/comps/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CompRequest.UpdateDTO requestDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionComp");
        User sessionComp = userService.findById(sessionUser.getId());
        User user = compService.updateById(sessionComp, requestDTO);
        session.setAttribute("sessionComp", user);
        return ResponseEntity.ok(new ApiUtil<>(requestDTO));
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
    public List<ResumeResponse.CmrDTO> findAllApplicants(@RequestParam(name = "userId") Integer userId) {
        return compService.findAllAppli(userId);
    }

    @PostMapping("/api/find-applicants")
    public List<CompResponse.RusaDTO> findApplicants(@RequestParam(name = "jobsId") Integer jId) {
        return compService.findApplicants(jId);
    }

    @PostMapping("/api/find-no-resp")
    public List<ResumeResponse.CmrDTO> findNoResp(@RequestParam(name = "userId") Integer uId) {
        return compService.findNoResp(uId);
    }

}

