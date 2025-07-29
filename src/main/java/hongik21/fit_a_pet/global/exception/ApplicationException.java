package hongik21.fit_a_pet.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    CustomErrorCode errorCode;
}
