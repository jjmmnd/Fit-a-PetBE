package hongik21.fit_a_pet.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank(message = "이메일은 필수로 입력해야 합니다.")
    @Pattern(
            regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?",
            message = "이메일 형식이 올바르지 않습니다."
    )
    private String email;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    private String password;
}
