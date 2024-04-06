package shop.mtcoding.blog._core.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Null;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception500;
import shop.mtcoding.blog._core.util.JwtUtil;
import shop.mtcoding.blog.domain.user.SessionUser;


public class LoginInterceptor implements HandlerInterceptor {
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
            if (sessionUser.getRole() == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("sessionUser", sessionUser);
            }
            if (sessionUser.getRole() == 2) {
                HttpSession session = request.getSession();
                session.setAttribute("sessionComp", sessionUser);
            }
            return true;
        } catch (TokenExpiredException e) {
            throw new Exception401("토큰 만료시간이 지났어요. 다시 로그인하세요");
        } catch (JWTDecodeException e) {
            throw new Exception401("토큰이 유효하지 않습니다");
        } catch (Exception e) {
            e.printStackTrace(); // 개발 진행 시 TEST 보기
            throw new Exception500(e.getMessage()); // 알 수 없는 오류 이니깐 500으로 다 던져 준다.
        }
    }

}
