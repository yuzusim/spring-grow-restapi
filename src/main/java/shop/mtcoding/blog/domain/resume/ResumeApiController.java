package shop.mtcoding.blog.domain.resume;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.domain.user.SessionUser;
import shop.mtcoding.blog.domain.user.User;
import shop.mtcoding.blog.domain.user.UserService;

@RequiredArgsConstructor
@RestController
public class ResumeApiController {
    private final ResumeService resumeService;
    private final UserService userService;
    private final HttpSession session;

    // 이력서 삭제
    @DeleteMapping("/api/resumes/{resumeId}")
    public ResponseEntity<?> delete(@PathVariable Integer resumeId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        resumeService.delete(resumeId, sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 이력서 수정 페이지 요청
    @GetMapping("/api/resume/{resumeId}/update-form")
    public ResponseEntity<?> updateFrom(@PathVariable Integer resumeId) {
        ResumeResponse.UpdateDTO respDTO = resumeService.updateForm(resumeId);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 이력서 상세보기 페이지 요청 (개인로그인)
    @GetMapping("/api/resumes/resume-detail/{resumeId}")
    public ResponseEntity<?> resumeDetail(@PathVariable Integer resumeId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());
        ResumeResponse.DetailDTO respDTO = resumeService.resumeDetail(resumeId,user);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 이력서 상세보기 페이지 요청 (기업로그인)
    @GetMapping("/api/resumes/resume-detail2/{id}")
    public ResponseEntity<?> compResumeDetail(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User newSessionUser = userService.findById(sessionUser.getId());

        ResumeResponse.DetailDTO2 respDTO = resumeService.resumeDetail2(id, newSessionUser);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 이력서 작성
    @PostMapping("/api/resumes")
    public ResponseEntity<?> save(@Valid @RequestBody ResumeRequest.SaveDTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());
        ResumeResponse.SaveDTO respDTO = resumeService.save(user, reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }



    @PutMapping("/api/resumes/{resumeId}")
    public ResponseEntity<?> update(@PathVariable Integer resumeId, @Valid @RequestBody ResumeRequest.UpdateDTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ResumeResponse.ResumeUpdateDTO respDTO = resumeService.update(resumeId, sessionUser.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

//    @DeleteMapping("/resume/{id}/delete")
//    public ResponseEntity<?> delete(@PathVariable Integer id) {
//        resumeService.delete(id);
//
//        return ResponseEntity.ok(new ApiUtil<>(null));
//    }
}
