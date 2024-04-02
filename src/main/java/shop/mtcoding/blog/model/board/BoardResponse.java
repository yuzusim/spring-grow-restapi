package shop.mtcoding.blog.model.board;

import lombok.Data;
import shop.mtcoding.blog.model.reply.Reply;
import shop.mtcoding.blog.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {


    // 글수정 완료
    @Data
    public static class UpdateDTO {
        private int id;
        private String title;
        private String content;

        public UpdateDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    // 게시글 상세보기 완료
    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username; // 게시글 작성자 이름
        private boolean isOwner;
        private List<ReplyDTO> replies = new ArrayList<>();

        public DetailDTO(Board board, User sessionUserId, List<Reply> repliesList) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getMyName(); // join 해서 가져왔음
            this.isOwner = false;
            if (sessionUserId != null) {
                if (sessionUserId.getId() == userId) isOwner = true;
            }

            this.replies = repliesList.stream().map(reply -> new ReplyDTO(reply, sessionUserId.getId())).toList();
        }

        @Data
        public class ReplyDTO {
            private int id;
            private String comment;
            private int userId; // 댓글 작성자 아이디
            private String username; // 댓글 작성자 이름
            private boolean isOwner;

            public ReplyDTO(Reply reply, Integer sessionUserId) {
                this.id = reply.getId(); // lazy loading 발동
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getMyName(); // lazy loading 발동 (in query)
                this.isOwner = false;
                if (sessionUserId != null) {
                    if (sessionUserId == userId) isOwner = true;
                }
            }
        }
    }

    // 글쓰기 완료
    @Data
    public static class SaveDTO {
        private int id;
        private String title;
        private String content;

        public SaveDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    // 글목록조회 완료
    @Data
    public static class BoardHomeDTO {
        private int id;
        private String title;

        public BoardHomeDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }


}







