package hongik21.fit_a_pet.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {

    // 1000

    // 2000: Token Error
    WRONG_TOKEN(HttpStatus.UNAUTHORIZED, 2000, "유효하지 않은 토큰입니다."),

    // 3000: Member Error
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 3000, "유저를 찾지 못했습니다."),
    EMAIL_VERIFY_FAILED(HttpStatus.BAD_REQUEST, 3001, "인증번호가 일치하지 않습니다.");


    private final HttpStatus status;
    private final Integer code;
    private final String message;
}
