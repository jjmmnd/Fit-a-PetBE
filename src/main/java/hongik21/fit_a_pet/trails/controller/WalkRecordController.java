package hongik21.fit_a_pet.trails.controller;

import hongik21.fit_a_pet.accounts.dto.JoinRequest;
import hongik21.fit_a_pet.accounts.dto.JoinResponse;
import hongik21.fit_a_pet.global.CommonResponse;
import hongik21.fit_a_pet.global.exception.ApplicationException;
import hongik21.fit_a_pet.global.jwt.CustomMemberDetails;
import hongik21.fit_a_pet.global.jwt.CustomMemberDetailsService;
import hongik21.fit_a_pet.global.jwt.JwtProvider;
import hongik21.fit_a_pet.trails.dto.WalkRecordRequestDTO;
import hongik21.fit_a_pet.trails.dto.WalkRecordResponseDTO;
import hongik21.fit_a_pet.trails.service.WalkRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trails")
public class WalkRecordController {
    private final WalkRecordService walkRecordService;

    @PostMapping()
    public CommonResponse<WalkRecordResponseDTO> registerTrailRecord(@RequestBody WalkRecordRequestDTO request) throws ApplicationException {
        // JWT 필터에서 이미 인증했으므로 SecurityContext에서 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  // userName을 email로 설정했었음

        WalkRecordResponseDTO response = walkRecordService.register(email, request);
        return CommonResponse.onSuccess(response,"산책 기록이 성공했습니다.");
    }

    @GetMapping("/doTest")
    public String doTest(){
        System.out.println("test");
        return "테스트 성공";
    }
}
