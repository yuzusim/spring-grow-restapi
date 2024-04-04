package shop.mtcoding.blog._core.util;


import lombok.Builder;
import lombok.Data;

@Data
public class ApiResponse {
    private Object body1;
    private Object body2;

    @Builder
    public ApiResponse(Object body1, Object body2) {
        this.body1 = body1;
        this.body2 = body2;
    }
}
