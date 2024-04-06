package shop.mtcoding.blog.domain.apply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.blog._core.util.ApiUtil;

@RequiredArgsConstructor
@RestController
public class ApplyApiController {
    private final ApplyService applyService;

    // 댓글 삭제
    @PostMapping("/api/apply/cancel")
    public ResponseEntity<?> applyCancel (@RequestParam("jobsId") Integer jobsId, @RequestParam("resumeId") Integer resumeId) {
        applyService.cancel(jobsId, resumeId);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 댓글 쓰기
    @PostMapping("/api/jobs/apply")
    public ResponseEntity<?> applySave(@RequestParam(name = "jobsId") Integer jobsId, @RequestParam(name = "resumeId") Integer resumeId, ApplyRequest.SaveDTO reqDTO) {
        ApplyResponse.NewApplyDTO respDTO = applyService.newApply(jobsId, resumeId, reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
