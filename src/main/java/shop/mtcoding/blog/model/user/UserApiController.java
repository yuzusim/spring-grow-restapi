package shop.mtcoding.blog.model.user;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/user/{id}/user-home")
    public ResponseEntity<?> userHome (@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        UserResponse.UserHomeDTO userHomeDTO = userService.userHome(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(userHomeDTO));
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

    @PostMapping("/api/find-jobs-resume")
    public List<UserResponse.UrsDTO> findAllJobsByResumeId(@RequestParam(name = "resumeId") Integer resumeId, HttpServletRequest request){
        List<UserResponse.UrsDTO> ursDTOList = userService.ursDTOS(resumeId);
        //No 카운트 뽑으려고 for문 돌림
        for (int i = 0; i < ursDTOList.size(); i++) {
            ursDTOList.get(i).setId(i + 1);
        }
        request.setAttribute("ursDTOList", ursDTOList);

        return ursDTOList;
    }
}
