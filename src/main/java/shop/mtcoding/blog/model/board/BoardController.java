package shop.mtcoding.blog.model.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;

    // 글쓰기
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.save(reqDTO, sessionUser);
        return "redirect:/board/board-home";
    }

    // 글수정
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.update(id, sessionUser.getId(), reqDTO);
        System.out.println("============");
        return "redirect:/board/board-home";
    }

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

    @GetMapping("/board/board-home")
    public String boardHome(HttpServletRequest request) {
        List<Board> boardList = boardService.findAll();
        request.setAttribute("boardList", boardList);
        return "/board/board-home";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }


    // 게시글 상세보기
    // SSR은 DTO를 굳이 만들필요가 없다. 필요한 데이터만 랜더링해서 클라이언트에게 전달할것이니까!!
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.findByIdJoinUser(id, sessionUser);

        request.setAttribute("board", board);

        // 이 로고가 찍히면서 레이지 로딩이 될 것임
        System.out.println("서버 사이드 랜더링 직전에는 Board와 User만 조회된 상태이다~~~~~~");

        return "/board/board-detail";
    }


}
