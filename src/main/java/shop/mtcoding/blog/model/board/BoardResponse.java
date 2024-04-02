package shop.mtcoding.blog.model.board;

import lombok.Data;
import shop.mtcoding.blog.model.user.User;

import java.util.ArrayList;

public class BoardResponse {


    // 글 수정
    @Data
    public static class UpdateDTO {
        private int id;
        private String title;
        private String content;
        private UserDTO user;

        @Data
        public class UserDTO {
            private int id;
            private String username;

            public UserDTO(User user) {
                this.id = user.getId();
                this.username = user.getMyName();
            }
        }
    }

    // 글 상세보기
    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username;    //게시글 작성자 이름
        // private List<ReplyDTO> replies = new ArrayList<>();
        private boolean isOwner;


        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getMyName();
            this.isOwner = false;

            // 보더의 주인여부 확인
            this.isOwner = false;
            if (sessionUser != null) {
                if (sessionUser.getId() == board.getUser().getId()) {
                    isOwner = true;
                }
            }
        }
    }

    // 글쓰기
    @Data
    public static class SaveDTO {
        private int id;
        private String title;
        private String content;

        public SaveDTO(Board board) {
            this.id = id;
            this.title = title;
            this.content = content;
        }
    }

    // 글목록조회
    @Data
    public static class BoardHomeDTO {
        private int id;
        private String title;

        public BoardHomeDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }


    @Data
    public class UserDTO {
        private int id;
        private String username;

        public UserDTO(User user) {
            this.id = user.getId();
            this.username = user.getMyName();
        }
    }
}