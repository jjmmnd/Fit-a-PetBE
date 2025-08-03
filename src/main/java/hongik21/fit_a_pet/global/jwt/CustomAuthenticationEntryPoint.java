package hongik21.fit_a_pet.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hongik21.fit_a_pet.global.CommonResponse;
import hongik21.fit_a_pet.global.exception.CustomErrorCode;
import io.lettuce.core.json.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 요청 시 액세스 토큰 없이 들어오는 경우 에러 메시지를 전달함
 * 로그인이 필요한 리소스에 로그인 없이 접근할 때 401 발생
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        log.info("[CustomAuthenticationEntryPoint] :: {}", authException.getMessage());
        log.info("[CustomAuthenticationEntryPoint] :: {}", request.getRequestURI());
        log.info("[CustomAuthenticationEntryPoint] :: 토큰 정보가 만료되었거나 존재하지 않음");

        response.setStatus(CustomErrorCode.ACCESS_DENIED.getStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        CommonResponse<Object> myResponse = CommonResponse.onFailure(null, CustomErrorCode.ACCESS_DENIED);
        String responseBody = new ObjectMapper().writeValueAsString(myResponse);

        response.getWriter().write(responseBody);
    }
}
