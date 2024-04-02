package shop.mtcoding.blog.model.jobs;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.resume.ResumeService;

import shop.mtcoding.blog.model.user.User;



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
        JobsResponse.JobsDetailDTO detailDTO = jobsService.jobsDetailDTO(jobsId,sessionUser);
        System.out.println("detailDTO :"+detailDTO);

        //사용자 이력서 보유내역과 지원상태를 가져오는 ResumeApplyDTO
        ResumeResponse.ResumeStateDTO resumeApplyDTOList = resumeService.findAllResumeJoinApplyByUserIdAndJobsId(sessionUser.getId(), jobsId);
        request.setAttribute("resumeApplyDTOList", resumeApplyDTOList);
        request.setAttribute("detailDTO", detailDTO);
        return "jobs/jobs-detail";
    }

    // 끝

    // 지워도 될듯?
    @GetMapping("/jobs/write-jobs-form")
    public String writeJobsForm(HttpServletRequest request) {
        User sessionComp = (User)session.getAttribute("sessionComp");

        // sessionComp 라고 주면 오류나서 sessionC라고 하였음
        request.setAttribute("sessionC", sessionComp);

        return "/jobs/write-jobs-form";
    }
}
