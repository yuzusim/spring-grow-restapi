package shop.mtcoding.blog.domain.apply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.domain.user.User;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final ApplyService applyService;
    private final HttpSession session;


    @PostMapping("/apply/pass/update/{resumeId}")
    public String applyPassUpDate(@PathVariable Integer resumeId, @RequestParam("jobsId")Integer jobsId) {
        User user = (User) session.getAttribute("sessionComp");
        applyService.pass(resumeId, jobsId);

        return "redirect:/comp/" + user.getId() + "/comp-home?jobsId=" + jobsId;
    }

    @PostMapping("/apply/fail/update/{resumeId}")
    public String applyFailUpDate(@PathVariable Integer resumeId, @RequestParam("jobsId")Integer jobsId) {
        User user = (User) session.getAttribute("sessionComp");
        applyService.fail(resumeId, jobsId);

        return "redirect:/comp/" + user.getId() + "/comp-home?jobsId=" + jobsId;
    }
}

