package shop.mtcoding.blog.domain.reply;

import jakarta.validation.constraints.Size;
import lombok.Data;
import shop.mtcoding.blog.domain.board.Board;
import shop.mtcoding.blog.domain.user.User;

public class ReplyRequest {
    @Data
    public static class SaveDTO {
        private Integer boardId;
        @Size(max = 200, message = "본문은 200글자를 넘길 수 없습니다.")
        private String comment;

        public Reply toEntity(User sessionUser, Board board) {
            return Reply.builder()
                    .comment(comment)
                    .board(board)
                    .user(sessionUser)
                    .build();
        }
    }
}
