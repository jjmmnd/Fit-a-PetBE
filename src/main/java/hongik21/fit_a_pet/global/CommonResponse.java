package hongik21.fit_a_pet.global;

import hongik21.fit_a_pet.global.exception.CustomErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class CommonResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public static <T> CommonResponse<T> onSuccess(T data, String message) {
        return new CommonResponse<>(HttpStatus.OK.value(), message, data);
    }

    public static <T> CommonResponse<T> onFailure(T data, CustomErrorCode errorCode) {
        return new CommonResponse<>(errorCode.getStatus().value(), errorCode.getMessage(), data);
    }
}
