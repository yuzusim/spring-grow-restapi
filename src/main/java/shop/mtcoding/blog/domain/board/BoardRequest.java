package shop.mtcoding.blog.domain.board;

import lombok.Data;
import shop.mtcoding.blog.domain.user.User;

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
