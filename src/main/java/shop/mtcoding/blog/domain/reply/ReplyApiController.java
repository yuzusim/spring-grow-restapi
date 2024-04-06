package shop.mtcoding.blog.domain.reply;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.domain.user.SessionUser;
import shop.mtcoding.blog.domain.user.User;
import shop.mtcoding.blog.domain.user.UserService;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {
    private final ReplyService replyService;
    private final HttpSession session;
    private final UserService userService;

    // 댓글삭제 완료
    @DeleteMapping("/api/replies/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        replyService.deleteById(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(null));
    }

    // 댓글쓰기 완료
    @PostMapping("/api/replies")
    public ResponseEntity<?> save(@Valid @RequestBody ReplyRequest.SaveDTO reqDTO){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());
        ReplyResponse.ReplyDTO respDTO = replyService.save(reqDTO, user);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
