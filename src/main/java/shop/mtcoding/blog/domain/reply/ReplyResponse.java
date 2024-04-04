package shop.mtcoding.blog.domain.reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import shop.mtcoding.blog.domain.user.User;

public class ReplyResponse {
    @AllArgsConstructor
    @Data
    public static class ReplyDTO {
        private Integer replyId;
        private Integer userId;
        private String comment;
        private String username;
        private Boolean replyOwner; // 게시글 주인 여부

        public ReplyDTO(Reply reply, User sessionUser) {
            this.replyId = reply.getId();
            this.userId = reply.getUser().getId();
            this.comment = reply.getComment();
            this.username = reply.getUser().getMyName();

            if (sessionUser == null) replyOwner = false;
            else replyOwner = sessionUser.getId().equals(userId);
        }
    }
}
