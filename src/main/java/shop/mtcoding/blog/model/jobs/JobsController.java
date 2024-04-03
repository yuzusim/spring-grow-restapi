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
    private final HttpSession session;
}
