package shop.mtcoding.blog.model.resume;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserService;


@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;
    private final UserService userService;


    @GetMapping("/resume/{id}/manage-resume")
    public String manageResume(@PathVariable Integer id) {

        return "/resume/manage-resume";
    }

    @GetMapping("/resume/write-resume-form")
    public String writeResumeForm(){

        return "/resume/write-resume-form";
    }

    @GetMapping("/resume/{id}/update-resume-form")
    public String updateResumeForm(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        request.setAttribute("sessionU", sessionUser);

        ResumeResponse.UpdateDTO resume = resumeService.updateForm(id);
        request.setAttribute("resume", resume);

        return "/resume/update-resume-form";
    }

    @PostMapping("/resume/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO reqDTO, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        //해당 부분 redirect 해보고 틀렸으면 본인이 수정
        resumeService.update(id, sessionUser.getId(), reqDTO);

        return "redirect:/user/" + id + "/user-home";
    }


    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.delete(id);

        return "redirect:/user/" + sessionUser.getId() + "/user-home";

    }


}
