package shop.mtcoding.blog.model.comp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.resume.ResumeService;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CompController {
    private final CompService compService;
    private final HttpSession session;


    @PostMapping("/comp/join")
    public String compJoin(@RequestParam(name = "role") Integer role, CompRequest.CompJoinDTO reqDTO) {
        User user = compService.join(role, reqDTO);
        session.setAttribute("sessionComp", user);
        return "redirect:/comp/read-resume";
    }

    @GetMapping("/comp/profile-update-form")
    public String profileUpdateForm(HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("imgFileName", sessionUser.getImgFileName());
        return "/comp/profile-update-form";
    }


}