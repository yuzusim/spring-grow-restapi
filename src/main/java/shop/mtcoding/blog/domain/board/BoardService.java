package shop.mtcoding.blog.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.domain.reply.Reply;
import shop.mtcoding.blog.domain.reply.ReplyJPARepository;
import shop.mtcoding.blog.domain.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepo;
    private final ReplyJPARepository replyJPARepo;

    // 글삭제하기 완료
    @Transactional
    public void deleteById(Integer boardId, Integer sessionUserId) {
        Board board = boardJPARepo.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        // 2. 권한처리
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }
        
        boardJPARepo.deleteById(boardId);
    }

    // 글수정 완료
    @Transactional
    public BoardResponse.UpdateDTO update(int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO) {
        // 1. 더티체킹 하기 위해 조회 및 예외처리
        Board board = boardJPARepo.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        // 2. 권한처리
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        // 3. 글 수정하기
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());

        return new BoardResponse.UpdateDTO(board);
    } // 더티체킹

    // 글목록조회 완료
    public List<BoardResponse.HomeDTO> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Board> boardList = boardJPARepo.findAll(sort);
        return boardList.stream().map(board -> new BoardResponse.HomeDTO(board)).toList();
    }

    // 글쓰기 완료
    @Transactional
    public Board save(@RequestBody BoardRequest.SaveDTO reqDTO, User sessionUser) {
        return boardJPARepo.save(reqDTO.toEntity(sessionUser));
    }

    // 글상세보기 완료
    public BoardResponse.DetailDTO findByIdJoinUser(int boardId, User sessionUser) {
        Board board = boardJPARepo.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        return new BoardResponse.DetailDTO(board, sessionUser, board.getReplies());
    }
}
