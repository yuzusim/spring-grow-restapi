package shop.mtcoding.blog.domain.board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import shop.mtcoding.blog.domain.user.User;

public class BoardRequest {

    @Data
     public static class UpdateDTO{

        @Size(max = 20, message = "제목이 작성되지 않았거나 20자 이상입니다.")
        @NotEmpty(message = "제목을 작성하여 주십시오.")
        private String title;

        @Size(max = 100, message = "내용이 작성되지 않았거나 100자 이상입니다.")
        @NotEmpty(message = "내용을 작성하여 주십시오.")
        private String content;
    }

    @Data
    public static class SaveDTO{
        @Size(max = 20, message = "제목이 작성되지 않았거나 20자 이상입니다.")
        @NotEmpty(message = "제목을 작성하여 주십시오.")
        private String title;
        @Size(max = 100, message = "내용이 작성되지 않았거나 100자 이상입니다.")
        @NotEmpty(message = "내용을 작성하여 주십시오.")
        private String content;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }
}
