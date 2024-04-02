package shop.mtcoding.blog.model.jobs;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class JobsApiController {

    private final HttpSession session;
    private final JobsService jobsService;

    @GetMapping("/api/jobs/info")
    public ResponseEntity<?> jobsInfo () {
        List<JobsResponse.ListDTO> listDTOS = jobsService.listDTOS();
        return ResponseEntity.ok(new ApiUtil<>(listDTOS));
    }

    // 하다 도망친거 - 승진
    @PostMapping("/api/jobs/save")
    public @ResponseBody ResponseEntity<?> save (@RequestBody JobsRequest.JobsSaveDTO reqDTO) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        Jobs jobs = jobsService.save(sessionComp, reqDTO);
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
}
