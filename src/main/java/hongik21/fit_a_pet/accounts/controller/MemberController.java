package hongik21.fit_a_pet.accounts.controller;

import hongik21.fit_a_pet.accounts.dto.MailRequest;
import hongik21.fit_a_pet.accounts.dto.MailVerifyRequest;
import hongik21.fit_a_pet.accounts.service.MemberService;
import hongik21.fit_a_pet.global.CommonResponse;
import hongik21.fit_a_pet.global.exception.CustomErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/email")
    public CommonResponse<Object> sendVerifyMail(@RequestBody MailRequest request) throws Exception {

        memberService.sendCodeToEmail(request.getEmail());
        return CommonResponse.onSuccess(null, "인증 메일을 전송했습니다.");
    }

    @PostMapping("/email/verify")
    public CommonResponse<Object> verifyMailCode(@RequestBody MailVerifyRequest request) {

        if(memberService.verifyMailCode(request.getEmail(), request.getCode()))
            return CommonResponse.onSuccess(null, "이메일 인증을 성공했습니다.");
        else
            return CommonResponse.onFailure(null, CustomErrorCode.EMAIL_VERIFY_FAILED);
    }
}
