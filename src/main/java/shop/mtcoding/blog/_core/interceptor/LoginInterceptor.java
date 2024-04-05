package shop.mtcoding.blog._core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Null;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.util.JwtUtil;
import shop.mtcoding.blog.domain.user.SessionUser;


public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle............");

        // Bearer jwt 토큰 으로 들어오는 것이 프로토콜이다.
        String jwt = request.getHeader("Authorization");
        System.out.println(jwt);
        jwt = jwt.replace("Bearer ", "");

        // 검증
        try {
            SessionUser sessionUser = JwtUtil.verify(jwt);
            System.out.println(jwt);
            if (sessionUser.getRole() == 1){
                HttpSession session = request.getSession();
                session.setAttribute("sessionUser", sessionUser);
            }
            if (sessionUser.getRole() == 2){
                HttpSession session = request.getSession();
                session.setAttribute("sessionComp", sessionUser);
            }
            return true;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
