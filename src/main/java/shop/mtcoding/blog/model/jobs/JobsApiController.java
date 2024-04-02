package shop.mtcoding.blog.model.jobs;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/resume/resume-detail/{resumeId}")
    public ResponseEntity<?> resumeDetail(@PathVariable Integer resumeId, @RequestParam Integer jobsId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User sessionComp = (User) session.getAttribute("sessionComp");
        ResumeResponse.DetailDTO respDTO = resumeService.resumeDetail(resumeId, jobsId, sessionUser, sessionComp);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/jobs/info")
    public ResponseEntity<?> jobsInfo () {
        List<JobsResponse.ListDTO> listDTOS = jobsService.listDTOS();
        return ResponseEntity.ok(new ApiUtil<>(listDTOS));
    }

    // 하다 도망친거 - 승진
    @PostMapping("/jobs/save")
    public @ResponseBody ResponseEntity<?> save (@RequestBody JobsRequest.JobsSaveDTO reqDTO) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        Jobs jobs = jobsService.save(sessionComp, reqDTO);
        return ResponseEntity.ok(new ApiUtil(jobs));
    }


    @DeleteMapping("/jobs/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        jobsService.delete(id);
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @GetMapping("/jobs/{jobsId}/update-jobs-form")
    public ResponseEntity<?> updateForm (@PathVariable Integer jobsId) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        JobsResponse.JobUpdateDTO job = jobsService.updateForm(jobsId, sessionComp.getId());
        return ResponseEntity.ok(new ApiUtil(job));
    }

    @PutMapping("/jobs/{id}/update")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody JobsRequest.UpdateDTO reqDTO) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        JobsResponse.UpdateDTO respDTO = jobsService.update(id, reqDTO, sessionComp);

        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
