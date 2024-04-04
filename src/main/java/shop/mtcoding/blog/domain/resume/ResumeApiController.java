package shop.mtcoding.blog.domain.resume;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.domain.user.SessionUser;
import shop.mtcoding.blog.domain.user.User;
import shop.mtcoding.blog.domain.user.UserService;

@RequiredArgsConstructor
@RestController
public class ResumeApiController {
    private final ResumeService resumeService;
    private final HttpSession session;
    private final UserService userService;

    @DeleteMapping("/api/resumes/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        resumeService.delete(id);

        return ResponseEntity.ok(new ApiUtil<>(null));

    }

    @GetMapping("/api/resume/{resumeId}/update-form")
    public ResponseEntity<?> updateFrom(@PathVariable Integer resumeId) {

        ResumeResponse.UpdateDTO respDTO = resumeService.updateForm(resumeId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/resume/resume-detail/{resumeId}")
    public ResponseEntity<?> resumeDetail(@PathVariable Integer resumeId, @RequestParam Integer jobsId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());
        SessionUser sessionComp = (SessionUser) session.getAttribute("sessionComp");
        User comp = userService.findById(sessionComp.getId());
        ResumeResponse.DetailDTO respDTO = resumeService.resumeDetail(resumeId, jobsId, user, comp);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/api/resumes")
    public ResponseEntity<?> save(@RequestBody ResumeRequest.SaveDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());
        ResumeResponse.SaveDTO respDTO = resumeService.save(user, reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/resumes/resume-detail2/{id}")
    public ResponseEntity<?> resumeDetail2(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User newSessionUser = userService.findById(sessionUser.getId());

        ResumeResponse.DetailDTO2 respDTO = resumeService.resumeDetail2(id, newSessionUser);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/comps/comp-resume-detail/{id}")
    public ResponseEntity<?> compResumeDetail(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User newSessionUser = userService.findById(sessionUser.getId());

        ResumeResponse.CompDetailDTO2 respDTO = resumeService.CompResumeDetail2(id, newSessionUser);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/api/resumes/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ResumeRequest.UpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        //해당 부분 redirect 해보고 틀렸으면 본인이 수정
        ResumeResponse.ResumeUpdateDTO respDTO = resumeService.update(id, sessionUser.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

//    @DeleteMapping("/resume/{id}/delete")
//    public ResponseEntity<?> delete(@PathVariable Integer id) {
//        resumeService.delete(id);
//
//        return ResponseEntity.ok(new ApiUtil<>(null));
//    }
}
