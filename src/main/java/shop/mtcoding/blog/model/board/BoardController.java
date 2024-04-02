package shop.mtcoding.blog.model.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;


    @GetMapping("/board/{id}/board-update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardService.updateForm(id);
        request.setAttribute("board", board);
        System.out.println("board : "+board);
        return "/board/update-form";
    }

    // 글삭제
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.deleteById(id, sessionUser.getId());
        return "redirect:/board/board-home";
    }

    // 글 상세보기
    @GetMapping("/board/{id}/detail")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.findByIdJoinUser(id, sessionUser);

        request.setAttribute("board", board);

        // 이 로고가 찍히면서 레이지 로딩이 될 것임
        System.out.println("서버 사이드 랜더링 직전에는 Board와 User만 조회된 상태이다~~~~~~");

        return "/board/board-detail";
    }

    // 글수정
//    @PostMapping("/board/{id}/update")
//    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        boardService.update(id, sessionUser.getId(), reqDTO);
//        System.out.println("============");
//        return "redirect:/board/board-home";
//    }

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.update(id, sessionUser.getId(), reqDTO);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    // 글쓰기 완료
    @PostMapping("/api/boards")
    public ResponseEntity<ApiUtil> save(@RequestBody BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.save(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    // TODO : saveForm 나중에 지우기
    //    @GetMapping("/board/save-form")
//    public String saveForm() {
//        return "board/save-form";
//    }

    // 글 목록보기 완료
    @GetMapping("/board/board-home")
    public ResponseEntity<?> boardHome() {
        List<BoardResponse.BoardHomeDTO> respDTO = boardService.findAll();
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

}
