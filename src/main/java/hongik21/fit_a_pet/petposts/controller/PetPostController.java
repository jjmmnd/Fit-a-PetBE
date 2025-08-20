package hongik21.fit_a_pet.petposts.controller;

import hongik21.fit_a_pet.global.CommonResponse;
import hongik21.fit_a_pet.global.exception.ApplicationException;
import hongik21.fit_a_pet.petposts.dto.PetPostEditRequest;
import hongik21.fit_a_pet.petposts.dto.PetPostEditResponse;
import hongik21.fit_a_pet.petposts.dto.PetPostWriteRequest;
import hongik21.fit_a_pet.petposts.dto.PetPostWriteResponse;
import hongik21.fit_a_pet.petposts.service.PetPostService;
import hongik21.fit_a_pet.posts.dto.PostWriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/petposts")
public class PetPostController {

    private final PetPostService petPostService;

    @PostMapping()
    public CommonResponse<PetPostWriteResponse> petWrite(
        @RequestBody PetPostWriteRequest request) throws ApplicationException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        PetPostWriteResponse response = petPostService.petWrite(email, request);
        return CommonResponse.onSuccess(response,"펫 포스트 작성에 성공했습니다");
    }


    @PutMapping("/{petPostId}")
    public CommonResponse<PetPostEditResponse> petEdit(
            @RequestBody PetPostEditRequest request,
            @PathVariable("petPostId") Long petPostId) throws ApplicationException{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        PetPostEditResponse response = petPostService.petEdit(request, petPostId, email);
        return CommonResponse.onSuccess(response,"펫 포스트 수정에 성공했습니다.");
    }


}
