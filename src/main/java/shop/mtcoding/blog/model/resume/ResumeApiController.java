package shop.mtcoding.blog.model.resume;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserService;

@RequiredArgsConstructor
@RestController
public class ResumeApiController {
    private final ResumeService resumeService;
    private final HttpSession session;
    private final UserService userService;

    @DeleteMapping("/api/resumes/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
     //  User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.delete(id);

        return ResponseEntity.ok(new ApiUtil<>(null));

    }

    @PostMapping("/resume/save")
    public ResponseEntity<?> save(@RequestBody ResumeRequest.SaveDTO reqDTO){
        resumeService.save(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }

    @GetMapping("/resume/resume-detail2/{id}")
    public ResponseEntity<?> resumeDetail2(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.findById(sessionUser.getId());

        ResumeResponse.DetailDTO2 respDTO = resumeService.resumeDetail2(id, newSessionUser);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/comp/comp-resume-detail/{id}")
    public ResponseEntity<?> compResumeDetail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.findById(sessionUser.getId());

        ResumeResponse.CompDetailDTO2 respDTO = resumeService.CompResumeDetail2(id, newSessionUser);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/resume/{id}/update")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ResumeRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        //해당 부분 redirect 해보고 틀렸으면 본인이 수정
        ResumeResponse.ResumeUpdateDTO respDTO = resumeService.update(id, sessionUser.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @DeleteMapping("/resume/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        resumeService.delete(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
