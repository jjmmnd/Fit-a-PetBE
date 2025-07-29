package hongik21.fit_a_pet.accounts.dto;

import lombok.Getter;

@Getter
public class MailVerifyRequest {
    private String email;
    private String code;
}
