package shop.mtcoding.blog.model.reply;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.user.User;

@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;


}
