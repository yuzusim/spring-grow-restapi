package shop.mtcoding.blog.model.user;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

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
