package shop.mtcoding.blog.model.comp;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CompApiController {
    private final CompService compService;
    private final HttpSession session;

    @GetMapping("/comp/comp-manage")
    public ResponseEntity<?> compManage () {
        User sessionUser = (User) session.getAttribute("sessionUser");
        CompResponse.CompManageDTO  compManageDTO = compService.compManage(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(compManageDTO));
    }

    @PostMapping("/api/find-all-jobs")
    public CompResponse.CompManageDTO compManageDTO (@RequestParam(name = "userId") Integer userId){
        return compService.compManage(userId);
    }

    @PostMapping("/api/find-all-applicants")
    public List<ResumeResponse.CmrDTO> findAllApplicants (@RequestParam(name = "userId") Integer userId){
        return compService.findAllAppli(userId);
    }

    @PostMapping("/api/find-applicants")
    public List<CompResponse.RusaDTO> findApplicants (@RequestParam(name = "jobsId") Integer jId){
        return compService.findApplicants(jId);
    }

    @PostMapping("/api/find-no-resp")
    public List<ResumeResponse.CmrDTO> findNoResp(@RequestParam(name = "userId") Integer uId){
        return compService.findNoResp(uId);
    }

}

