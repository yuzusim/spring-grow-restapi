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



    // 지워도 될듯?
    @GetMapping("/jobs/write-jobs-form")
    public String writeJobsForm(HttpServletRequest request) {
        User sessionComp = (User)session.getAttribute("sessionComp");

        // sessionComp 라고 주면 오류나서 sessionC라고 하였음
        request.setAttribute("sessionC", sessionComp);

        return "/jobs/write-jobs-form";
    }
}
