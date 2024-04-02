package shop.mtcoding.blog.model.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepo;

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

    // 글수정
    public Board updateForm(int boardId) {
        Board board = boardJPARepo.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));
        return board;
    }

    @Transactional
    public Board update(int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO) {
        // 1. 더티체킹 하기 위해 조회 및 예외처리
        Board board = boardJPARepo.findById(boardId)
                .orElseThrow(() -> new Exception404("{게시글을 찾을 수 없습니다."));

        // 2. 권한처리
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        // 3. 글 수정하기
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());

        return board;
    } // 더티체킹


    // 글목록조회 완료
    public List<BoardResponse.BoardHomeDTO> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Board> boardList = boardJPARepo.findAll(sort);
        return boardList.stream().map(board -> new BoardResponse.BoardHomeDTO(board)).toList();
    }

    // 글쓰기 완료
    @Transactional
    public Board save(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        return boardJPARepo.save(reqDTO.toEntity(sessionUser));
    }

    // 글상세보기
    public Board findByIdJoinUser(Integer boardId, User sessionUser) {
        Board board = boardJPARepo.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("{게시글을 찾을 수 없습니다."));

        boolean isBoardOwner = false;
        if (sessionUser != null) {
            if (sessionUser.getId() == board.getUser().getId()) {
                isBoardOwner = true;
            }
        }

        board.setBoardOwner(isBoardOwner);

        // 댓글은 forEach문 돌리기 (N이니까)
        board.getReplies().forEach(reply -> {
            boolean isReplyOwner = false;

            // 댓글 주인 여부
            if (sessionUser != null) {
                if (reply.getUser().getId() == sessionUser.getId()) {
                    isReplyOwner = true;
                }
            }
            reply.setReplyOwner(isReplyOwner);
        });

        return board;
    }


}
