package shop.mtcoding.blog.domain.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog._core.util.JwtUtil;
import shop.mtcoding.blog._core.util.JwtVO;
import shop.mtcoding.blog.domain.comp.CompRequest;
import shop.mtcoding.blog.domain.comp.CompResponse;
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
    @PutMapping("/api/users/comps")
    public ResponseEntity<?> updateComp(@Valid @RequestBody UserRequest.UpdateCompDTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        UserResponse.UpdatedCompDTO respDTO = userService.updateByCompId(sessionUser.getId(), reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
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

    // 회원가입
    @PostMapping("/users/join")
    public ResponseEntity<?> join(@Valid @RequestBody UserRequest.JoinDTO reqDTO, Errors errors) {
        User user = userService.join(reqDTO, reqDTO.getRole());
        String jwt = JwtUtil.create(user);
        UserResponse.JoinDTO respDTO = new UserResponse.JoinDTO(user);

        return ResponseEntity.ok()
                .header(JwtVO.HEADER, JwtVO.PREFIX + jwt)
                .body(new ApiUtil<>(respDTO));
    }

    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserRequest.LoginDTO reqDTO, Errors errors) {
        User user = userService.login(reqDTO);
        String jwt = JwtUtil.create(user);

        return ResponseEntity.ok()
                .header(JwtVO.HEADER, JwtVO.PREFIX + jwt)
                .body(new ApiUtil(null));
    }

    // 메인페이지 정보요청
    @GetMapping("/")
    public ResponseEntity<?> index() {
        List<JobsResponse.InfoDTO> respList = jobsService.indexDTOs();
        return ResponseEntity.ok(new ApiUtil(respList));
    }

    // 메인페이지에서 검색 정보 요청
    @PostMapping("/search")
    public ResponseEntity<?> indexKeyword(@RequestBody JobsRequest.KeywordDTO reqDTO) {
        List<JobsResponse.IndexSearchDTO> respList = jobsService.searchKeyword(reqDTO.getKeyword());
        return ResponseEntity.ok(new ApiUtil<>(respList));
    }

    // 이력서 관리 페이지 정보
    @GetMapping("/api/users/{id}/home")
    public ResponseEntity<?> userHome(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.HomeDTO userHomeDTO = userService.userHome(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(userHomeDTO));
    }

    // 회원가입시 username 중복 체크
    @GetMapping("/api/users/username-same-check")
    public ResponseEntity<?> usernameSameCheck(@RequestBody UserRequest.EmailDTO email) {
        User user = userService.findByEmail(email.getEmail());
        if (user == null) {
            return ResponseEntity.ok(new ApiUtil<>(true));
        } else {
            return ResponseEntity.ok(new ApiUtil<>(false));
        }
    }

    // ajax 이력서 지원내역 요청
    @PostMapping("/api/find-jobs-resume")
    public ResponseEntity<?> findAllJobsByResumeId(@RequestBody UserRequest.ResumeIdDTO resumeId) {
        List<UserResponse.FindJobsResumeDTO> respList = userService.findJobsResumeDTOS(resumeId.getResumeId());
        return ResponseEntity.ok(new ApiUtil<>(respList));
    }

    // 지원 내역 페이지 정보 요청
    @GetMapping("/api/user/resume-home")
    public ResponseEntity<?> resumeHome() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<UserResponse.resumeHomeDTO> respList = userService.resumeHome(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respList));
    }
}
