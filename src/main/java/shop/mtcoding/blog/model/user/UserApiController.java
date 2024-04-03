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
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsRequest;
import shop.mtcoding.blog.model.jobs.JobsResponse;
import shop.mtcoding.blog.model.jobs.JobsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;
    private final JobsService jobsService;


    @GetMapping("/")
    public ResponseEntity<?> index(HttpServletRequest request) {
        List<JobsResponse.ListDTO> respList = jobsService.listDTOS();
        request.setAttribute("listDTOS", respList);

        return ResponseEntity.ok(new ApiUtil(respList));
    }


    @PostMapping("/search")
    public ResponseEntity<?> indexKeyword(HttpServletRequest request, @RequestBody JobsRequest.KeywordDTO reqDTO) {

        List<JobsResponse.IndexSearchDTO> respList = jobsService.searchKeyword(reqDTO.getKeyword());
        request.setAttribute("jobsKeyword", respList);
        request.setAttribute("keyword", reqDTO.getKeyword());
        System.out.println("respList size : " + respList.size());

        return ResponseEntity.ok(new ApiUtil<>(respList));
    }


    @GetMapping("/api/users/{id}/user-home")
    public ResponseEntity<?> userHome (@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        UserResponse.UserHomeDTO userHomeDTO = userService.userHome(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(userHomeDTO));
    }

    @GetMapping("/api/users/username-same-check")
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
