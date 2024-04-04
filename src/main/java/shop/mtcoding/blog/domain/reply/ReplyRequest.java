package shop.mtcoding.blog.domain.reply;

import lombok.Data;
import shop.mtcoding.blog.domain.board.Board;
import shop.mtcoding.blog.domain.user.User;

public class ReplyRequest {
    @Data
    public static class SaveDTO {
        private Integer boardId;
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
