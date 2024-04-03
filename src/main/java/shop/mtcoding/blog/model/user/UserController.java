package shop.mtcoding.blog.model.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.jobs.JobsService;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final HttpSession session;

//     //user의 지원 내역
//     @GetMapping("/user/{id}/resume-home")
//     public String resumeHome(@PathVariable Integer id, HttpServletRequest request) {
//         User sessionUser = (User) session.getAttribute("sessionUser");

//         List<UserResponse.UserResumeSkillDTO> ursList = userService.userResumeSkillDTO(sessionUser.getId());
//         //No 카운트 뽑으려고 for문 돌림
//         for (int i = 0; i < ursList.size(); i++) {
//             ursList.get(i).setId(i + 1);
//         }
//         request.setAttribute("ursList", ursList);

//         return "/user/resume-home";
//     }


//     @GetMapping("/user/join-form")
//     public String joinForm() {
//         return "/user/join-form";
//     }


//     @DeleteMapping("/user/{id}")
//     public String delete() {
//         return "redirect:/";
//     }


    @PostMapping("/user/{id}/update")
    public String update(@PathVariable Integer id, CompRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        userService.updateById(sessionUser, requestDTO);
        User user = userService.findById(sessionUser.getId());
        session.setAttribute("sessionUser", user);

        return "redirect:/";
    }

    @GetMapping("/user/{id}/update-form")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.findById(sessionUser.getId());
        request.setAttribute("user", newSessionUser);

        return "/user/update-form";
    }
}