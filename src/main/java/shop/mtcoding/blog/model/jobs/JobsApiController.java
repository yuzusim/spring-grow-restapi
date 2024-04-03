package shop.mtcoding.blog.model.jobs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.resume.ResumeResponse;

import shop.mtcoding.blog.model.resume.ResumeService;

import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobsApiController {
    private final HttpSession session;
    private final JobsService jobsService;
    private final ResumeService resumeService;

    @GetMapping("/api/jobs/write-jobs-form")
    public ResponseEntity<?> writeJobsForm() {
        User sessionComp = (User)session.getAttribute("sessionComp");

        JobsResponse.writeJobsFormDTO writeJobsFormDTO = jobsService.writeJobsForm(sessionComp);

        return ResponseEntity.ok(new ApiUtil<>(writeJobsFormDTO));
    }

    @GetMapping("/api/jobs/info")
    public ResponseEntity<?> jobsInfo () {
        List<JobsResponse.ListDTO> listDTOS = jobsService.listDTOS();
        return ResponseEntity.ok(new ApiUtil<>(listDTOS));
    }

    @PostMapping("/api/jobs/save")
    public @ResponseBody ResponseEntity<?> save (@Valid @RequestBody JobsRequest.JobsSaveDTO reqDTO, Errors errors) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        JobsResponse.JonsSaveDTO jobs = jobsService.save(sessionComp, reqDTO);
        return ResponseEntity.ok(new ApiUtil(jobs));
    }

    @DeleteMapping("/api/jobs/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        jobsService.delete(id);
        return ResponseEntity.ok(new ApiUtil(null));
    }


    @GetMapping("/api/jobs/{jobsId}/update-jobs-form")
    public  ResponseEntity<?> updateForm (@PathVariable Integer jobsId) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        JobsResponse.JobUpdateDTO job = jobsService.updateForm(jobsId, sessionComp.getId());
        return ResponseEntity.ok(new ApiUtil(job));
    }


    @GetMapping("/api/jobs/{jobsId}/detail")
    public ResponseEntity<?> jobsDetail(@PathVariable Integer jobsId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new Exception401("인증되지 않았습니다.");
        }

        //사용자 이력서 보유내역과 지원상태를 가져오는 ResumeApplyDTO
        JobsResponse.JobResumeDetailDTO resumeApplyDTOList = jobsService.jobsDetailDTO(jobsId, sessionUser);

        return ResponseEntity.ok(new ApiUtil(resumeApplyDTOList));
    }

    @PutMapping("/api/jobs/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody JobsRequest.UpdateDTO reqDTO) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        JobsResponse.UpdateDTO respDTO = jobsService.update(id, reqDTO, sessionComp);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/resumes/{resumeId}/update-form")
    public ResponseEntity<?> updateFrom(@PathVariable Integer resumeId){

        ResumeResponse.UpdateDTO respDTO = resumeService.updateForm(resumeId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
