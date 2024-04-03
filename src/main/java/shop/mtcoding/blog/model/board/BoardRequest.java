package shop.mtcoding.blog.model.board;

import lombok.Data;
import shop.mtcoding.blog.model.user.User;

public class BoardRequest {

    @Data
    static class UpdateDTO{
        private String title;
        private String content;
    }

    @Data
    static class SaveDTO{
        private String title;
        private String content;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }
}
