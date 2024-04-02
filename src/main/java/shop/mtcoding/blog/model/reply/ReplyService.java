package shop.mtcoding.blog.model.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.board.Board;
import shop.mtcoding.blog.model.board.BoardJPARepository;
import shop.mtcoding.blog.model.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final BoardJPARepository boardJPARepo;
    private final ReplyJPARepository replyJPARepo;

    @Transactional
    public void deleteById(Integer replyId, Integer sessionUserId) {
        Reply reply = replyJPARepo.findById(replyId)
                .orElseThrow(() -> new Exception404("없는 댓글을 삭제할 수 없어요"));

        if (reply.getUser().getId() != sessionUserId) {
            throw new Exception403("댓글을 삭제할 권한이 없어요");

        }
        replyJPARepo.deleteById(replyId);

    }

    // 댓글쓰기
    @Transactional
    public Reply save(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = boardJPARepo.findById(reqDTO.getBoardId())
                .orElseThrow(() -> new Exception404("없는 게시글에 댓글을 작성할 수 없어요"));
        Reply reply = reqDTO.toEntity(sessionUser, board);
        return replyJPARepo.save(reply);
    }

}
