package hongik21.fit_a_pet.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class JoinRequest {

    @NotBlank(message = "이름은 필수로 입력해야 합니다.")
    private String name;

    @NotBlank(message = "이메일은 필수로 입력해야 합니다.")
    @Pattern(
            regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?",
            message = "이메일 형식이 올바르지 않습니다."
    )
    private String email;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    @Size(min = 8, max = 30)
    @Pattern(
            regexp = "^[a-zA-Z0-9~!@#$%^&*()]{8,30}",
            message = "비밀번호는 영어 대소문자, 숫자, 특수 문자로 구성돼야 합니다."
    )
    private String password;

    @NotBlank(message = "닉네임은 필수로 입력해야 합니다.")
    @Size(min = 5, max = 15)
    @Pattern(
            regexp = "^[a-zA-Z0-9_]{5,15}",
            message = "비밀번호는 영어 대소문자, 숫자, 언더바로 구성돼야 합니다."
    )
    private String nickname;
}