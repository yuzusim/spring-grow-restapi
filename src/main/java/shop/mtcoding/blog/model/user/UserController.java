package shop.mtcoding.blog.model.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.apply.ApplyResponse;
import shop.mtcoding.blog.model.apply.ApplyService;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsResponse;
import shop.mtcoding.blog.model.jobs.JobsService;
import shop.mtcoding.blog.model.resume.ResumeRequest;
import shop.mtcoding.blog.model.resume.ResumeService;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class UserController {
    private final JobsService jobsService;
    private final UserService userService;
    private final HttpSession session;
    private final ResumeService resumeService;
    private final ApplyService applyService;


    @GetMapping("/user/{id}/user-home")
    public ResponseEntity<?> userHome (@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        UserResponse.UserHomeDTO userHomeDTO = userService.userHome(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(userHomeDTO));
//        request.setAttribute("sessionUserId", sessionUser.getId());
//        return "/user/user-home";
    }


    //user의 지원 내역
    @GetMapping("/user/{id}/resume-home")
    public String resumeHome(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<UserResponse.UserResumeSkillDTO> ursList = userService.userResumeSkillDTO(sessionUser.getId());
        //No 카운트 뽑으려고 for문 돌림
        for (int i = 0; i < ursList.size(); i++) {
            ursList.get(i).setId(i + 1);
        }
        request.setAttribute("ursList", ursList);

        return "/user/resume-home";
    }


    @GetMapping("/user/join-form")
    public String joinForm() {
        return "/user/join-form";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, @RequestParam (value = "keyword", defaultValue = "") String keyword) {

        if (keyword.isBlank()) {
            List<JobsResponse.ListDTO> listDTOS = jobsService.listDTOS();
            request.setAttribute("listDTOS", listDTOS);

        } else {
            List<Jobs> jobsKeyword = jobsService.searchKeyword(keyword);
            request.setAttribute("jobsKeyword", jobsKeyword);
        }

        request.setAttribute("keyword", keyword);

        return "index";
    }

    @GetMapping("/api/user/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return new ApiUtil<>(true);
        } else {
            return new ApiUtil<>(false);
        }
    }

    @PostMapping("/user/join")
    public String join(@RequestParam(name = "role") Integer role, UserRequest.JoinDTO reqDTO) {
        User user = userService.join(reqDTO, role);
        session.setAttribute("sessionUser", user);
        return "redirect:/";
    }

    @DeleteMapping("/user/{id}")
    public String delete (){
        return "redirect:/";
    }


    @PostMapping("/user/login")
    public  ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO, HttpSession session) {
        User user = userService.login(reqDTO);

        if (user != null) {
            session.setAttribute("sessionUser", user);
            int role = user.getRole();
            if (role == 1) {
            } else if (role == 2) {
                session.setAttribute("sessionComp", user);
            }
        }
        return ResponseEntity.ok(new ApiUtil(null));
    }


    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/user/login-form")
    public String loginForm() {
        return "/user/login-form";
    }

    @GetMapping("/user/{id}/apply")
    public String offer(@PathVariable Integer id) {
        return "/user/apply";
    }

    @GetMapping("/user/{id}/scrap")
    public String scrap(@PathVariable Integer id) {
        return "/user/scrap";
    }

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



    // 이미지업로드용
    @PostMapping("/user/profile-upload")
    public String profileUpload() {

        return "redirect:/user/profile-update-form";
    }
    @GetMapping("/user/profile-update-form")
    public String profileUpdateForm(HttpServletRequest request) {
        return "/user/profile-update-form";
    }

}
