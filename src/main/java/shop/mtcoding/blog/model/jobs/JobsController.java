package shop.mtcoding.blog.model.jobs;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.resume.ResumeService;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class JobsController {
    private final JobsService jobsService;
    private final ResumeService resumeService;
    private final HttpSession session;

    // 채현
    @GetMapping("/jobs/jobs-detail/{jobsId}")
    public String jobsDetail(@PathVariable Integer jobsId, HttpServletRequest request){
        User sessionUser = (User)session.getAttribute("sessionUser");

        if (sessionUser == null){
            throw new Exception401("인증되지 않았습니다.");
        }

        //공고정보와 사용자정보를 가져오는 detailDTO
        JobsResponse.DetailDTO detailDTO = jobsService.DetailDTO(jobsId,sessionUser);
        System.out.println("detailDTO :"+detailDTO);

        //사용자 이력서 보유내역과 지원상태를 가져오는 ResumeApplyDTO
        ResumeResponse.ResumeStateDTO resumeApplyDTOList = resumeService.findAllResumeJoinApplyByUserIdAndJobsId(sessionUser.getId(), jobsId);
        request.setAttribute("resumeApplyDTOList", resumeApplyDTOList);
        request.setAttribute("detailDTO", detailDTO);
        return "jobs/jobs-detail";
    }

    // 끝
    @GetMapping("/jobs/info")
    public ResponseEntity<?> jobsInfo () {
        List<JobsResponse.ListDTO> listDTOS = jobsService.listDTOS();
        return ResponseEntity.ok(new ApiUtil<>(listDTOS));
    }

    // 지워도 될듯?
    @GetMapping("/jobs/write-jobs-form")
    public String writeJobsForm(HttpServletRequest request) {
        User sessionComp = (User)session.getAttribute("sessionComp");

        // sessionComp 라고 주면 오류나서 sessionC라고 하였음
        request.setAttribute("sessionC", sessionComp);

        return "/jobs/write-jobs-form";
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

    @PostMapping("/jobs/{id}/update")
    public String update(@PathVariable Integer id, JobsRequest.UpdateDTO reqDTO) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        jobsService.update(id, reqDTO, sessionComp);


        return "redirect:/comp/" + sessionComp.getId() + "/comp-home";
    }

    @GetMapping("/jobs/{jobsId}/update-jobs-form")
    public  ResponseEntity<?> updateForm (@PathVariable Integer jobsId) {
        User sessionComp = (User)session.getAttribute("sessionComp");
        JobsResponse.JobUpdateDTO job = jobsService.updateForm(jobsId, sessionComp.getId());
        return ResponseEntity.ok(new ApiUtil(job));
    }
}
