package shop.mtcoding.blog.domain.user;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog._core.util.JwtUtil;
import shop.mtcoding.blog._core.util.JwtVO;
import shop.mtcoding.blog.domain.comp.CompRequest;
import shop.mtcoding.blog.domain.jobs.JobsRequest;
import shop.mtcoding.blog.domain.jobs.JobsResponse;
import shop.mtcoding.blog.domain.jobs.JobsService;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;
    private final JobsService jobsService;


    // 사용자 정보 수정
    @PutMapping("/api/users")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequest.UpdateUserDTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());

        UserResponse.UserUpdateDTO updatedUser = userService.updateByUserId(user, reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(updatedUser));
    }

    // 기업 회원 정보수정
    @PutMapping("/api/comp-users")
    public ResponseEntity<?> updateComp (@Valid @RequestBody UserRequest.UpdateCompDTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());

        UserResponse.UpdatedCompDTO updatedUser = userService.updateByCompId(user, reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(updatedUser));
    }

    // 개인 사용자 정보 수정 페이지
    @GetMapping("/api/users")
    public ResponseEntity<?> updateUserForm() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.UpdateUserFormDTO respDTO = userService.updateUserForm(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 기업 사용자 정보 수정 페이지
    @GetMapping("/api/comp-users")
    public ResponseEntity<?> updateCompForm() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.UpdateCompFormDTO respDTO = userService.updateCompForm(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //user 회원가입 api
    @PostMapping("/users/join")
    public ResponseEntity<?> join(@Valid @RequestBody UserRequest.JoinDTO reqDTO, Errors errors) {
        UserResponse.UserJoinDTO respDTO = userService.join(reqDTO, reqDTO.getRole());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserRequest.LoginDTO reqDTO, HttpSession session, Errors errors) {
        User user = userService.login(reqDTO);
        String jwt = JwtUtil.create(user);

        return ResponseEntity.ok().header(JwtVO.HEADER, JwtVO.PREFIX + jwt).body(new ApiUtil(null));
    }

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


    @GetMapping("/api/users/{id}/home")
    public ResponseEntity<?> userHome (@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.UserHomeDTO userHomeDTO = userService.userHome(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(userHomeDTO));
    }

    @GetMapping("/api/users/username-same-check")
    public  ResponseEntity<?> usernameSameCheck(@RequestBody UserRequest.EmailDTO email) {
        User user = userService.findByEmail(email.getEmail());
        if (user == null) {
            return ResponseEntity.ok(new ApiUtil<>(true));
        } else {
            return ResponseEntity.ok(new ApiUtil<>(false));
        }
    }

    @PostMapping("/api/find-jobs-resume")
    public ResponseEntity<?> findAllJobsByResumeId(@RequestBody UserRequest.ResumeIdDTO resumeId, HttpServletRequest request){
        List<UserResponse.FindJobsResumeDTO> fjrDTOList = userService.findJobsResumeDTOS(resumeId.getResumeId());
        request.setAttribute("ursDTOList", fjrDTOList);
        return ResponseEntity.ok(new ApiUtil<>(fjrDTOList));
    }

    //user의 지원 내역
    @GetMapping("/api/user/{id}/resume-home")
    public ResponseEntity<?> resumeHome(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        List<UserResponse.UserResumeSkillDTO> respDTO = userService.userResumeSkillDTO(sessionUser.getId());
        //No 카운트 뽑으려고 for문 돌림
        for (int i = 0; i < respDTO.size(); i++) {
            respDTO.get(i).setId(i + 1);
        }

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
