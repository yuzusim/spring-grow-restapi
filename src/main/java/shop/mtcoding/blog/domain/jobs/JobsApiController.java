package shop.mtcoding.blog.domain.jobs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.domain.resume.ResumeResponse;

import shop.mtcoding.blog.domain.resume.ResumeService;

import shop.mtcoding.blog.domain.user.SessionUser;
import shop.mtcoding.blog.domain.user.User;
import shop.mtcoding.blog.domain.user.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobsApiController {
    private final HttpSession session;
    private final JobsService jobsService;
    private final ResumeService resumeService;
    private final UserService userService;


    // 기업 공고 작성 폼 페이지 요청
    @GetMapping("/api/jobs/write-jobs-form")
    public ResponseEntity<?> writeJobsForm() {
        SessionUser sessionUser = (SessionUser)session.getAttribute("sessionComp");
        User sessionComp = userService.findById(sessionUser.getId());
        JobsResponse.WriteJobsFormDTO writeJobsFormDTO = jobsService.writeJobsForm(sessionComp);

        return ResponseEntity.ok(new ApiUtil<>(writeJobsFormDTO));
    }

    // 기업 공고 목록 페이지 요청
    @GetMapping("/api/jobs/info")
    public ResponseEntity<?> jobsInfo () {
        List<JobsResponse.InfoDTO> respList = jobsService.jobsInfo();
        return ResponseEntity.ok(new ApiUtil<>(respList));
    }

    // 기업 공고 작성
    @PostMapping("/api/jobs/save")
    public @ResponseBody ResponseEntity<?> save (@Valid @RequestBody JobsRequest.JobsSaveDTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser)session.getAttribute("sessionComp");
        User sessionComp = userService.findById(sessionUser.getId());
        JobsResponse.JobsSaveDTO jobs = jobsService.save(sessionComp, reqDTO);
        return ResponseEntity.ok(new ApiUtil(jobs));
    }

    // 기업 공고 삭제
    @DeleteMapping("/api/jobs/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        jobsService.delete(id);
        return ResponseEntity.ok(new ApiUtil(null));
    }

    // 기업 공고 수정 페이지 요청
    @GetMapping("/api/jobs/{jobsId}/update-jobs-form")
    public  ResponseEntity<?> updateForm (@PathVariable Integer jobsId) {
        SessionUser sessionComp = (SessionUser)session.getAttribute("sessionComp");
        JobsResponse.JobUpdateDTO job = jobsService.updateForm(jobsId, sessionComp.getId());
        return ResponseEntity.ok(new ApiUtil(job));
    }

    // TODO 리팩토링 필요
    @GetMapping("/api/jobs/{jobsId}/detail")
    public ResponseEntity<?> jobsDetail(@PathVariable Integer jobsId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionComp");
        User user = userService.findById(sessionUser.getId());
        //사용자 이력서 보유내역과 지원상태를 가져오는 ResumeApplyDTO
        JobsResponse.JobResumeDetailDTO resumeApplyDTOList = jobsService.jobsDetailDTO(jobsId, user);

        return ResponseEntity.ok(new ApiUtil(resumeApplyDTOList));
    }

    // TODO 공고수정 : 리팩토링 필요
    @PutMapping("/api/jobs/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody JobsRequest.UpdateDTO reqDTO, Errors errors) {
        JobsResponse.UpdateDTO respDTO = jobsService.update(id, reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // 이력서 수정 페이지 요청
    @GetMapping("/api/resumes/{resumeId}/update-form")
    public ResponseEntity<?> updateFrom(@PathVariable Integer resumeId){

        ResumeResponse.UpdateDTO respDTO = resumeService.updateForm(resumeId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
