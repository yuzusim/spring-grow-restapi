package shop.mtcoding.blog.domain.comp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.domain.user.SessionUser;
import shop.mtcoding.blog.domain.user.User;
import shop.mtcoding.blog.domain.user.UserService;

@RequiredArgsConstructor
@RestController
public class CompController {
    private final HttpSession session;
    private final UserService userService;

    @GetMapping("/comp/profile-update-form")
    public String profileUpdateForm(HttpServletRequest request) {

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());
        request.setAttribute("imgFileName", user.getImgFileName());
        return "/comp/profile-update-form";
    }
}