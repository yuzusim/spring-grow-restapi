package shop.mtcoding.blog.model.board;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;
    private final HttpSession session;

    // 글삭제 완료
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.deleteById(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 글수정 완료
    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        //Board board = boardService.update(id, sessionUser.getId(), reqDTO);
        BoardResponse.UpdateDTO respDTO = boardService.update(id, sessionUser.getId(), reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 글 상세보기 완료
    @GetMapping("/api/boards/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO respDTO = boardService.findByIdJoinUser(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 글쓰기 완료
    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@Valid @RequestBody BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.save(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    // 글 목록보기 완료
    @GetMapping("/api/boards/board-home")
    public ResponseEntity<?> boardHome() {
        List<BoardResponse.BoardHomeDTO> respDTO = boardService.findAll();
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

}
