package shop.mtcoding.blog.domain.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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



    // 이미지업로드용
    @PostMapping("/user/profile-upload")
    public String profileUpload() {

        return "redirect:/user/profile-update-form";

    }
}