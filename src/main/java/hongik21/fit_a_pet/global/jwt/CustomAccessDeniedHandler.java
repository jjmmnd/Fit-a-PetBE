package hongik21.fit_a_pet.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hongik21.fit_a_pet.global.CommonResponse;
import hongik21.fit_a_pet.global.exception.CustomErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 권한이 없거나 접근할 수 없는 곳에 요청을 한 경우 403 에러 메시지 전달
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        log.info("[CustomAuthenticationEntryPoint] :: {}", accessDeniedException.getMessage());
        log.info("[CustomAuthenticationEntryPoint] :: {}", request.getRequestURI());
        log.info("[CustomAuthenticationEntryPoint] :: 토큰 정보가 만료되었거나 존재하지 않음");

        response.setStatus(CustomErrorCode.FORBIDDEN.getStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        CommonResponse<Object> myResponse = CommonResponse.onFailure(null, CustomErrorCode.FORBIDDEN);
        String responseBody = new ObjectMapper().writeValueAsString(myResponse);

        response.getWriter().write(responseBody);
    }
}
