package kr.co.doritos.todoservice.handler;

import kr.co.doritos.todoservice.common.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String ip = request.getRemoteAddr();

        String redirect = "";
        if (authentication.getAuthorities().stream()
                .anyMatch(e -> e.getAuthority().equals(UserRole.ROLE_ADMIN.getRole()))) {
            redirect = "/office";
        } else {
            redirect = "/";
        }

        log.info("[{}] 로그인 성공. email={}, role={}, redirect={}", ip, authentication.getName(), authentication.getAuthorities(), redirect);

        response.sendRedirect(redirect);
    }
}
