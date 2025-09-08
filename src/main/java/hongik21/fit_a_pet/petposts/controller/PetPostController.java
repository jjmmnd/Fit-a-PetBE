package hongik21.fit_a_pet.petposts.controller;

import hongik21.fit_a_pet.global.CommonResponse;
import hongik21.fit_a_pet.global.exception.ApplicationException;
import hongik21.fit_a_pet.petposts.dto.*;
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
    public CommonResponse<PetPostWriteResponse> writePetpost(
        @RequestBody PetPostWriteRequest request) throws ApplicationException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        PetPostWriteResponse response = petPostService.writePetpost(email, request);
        return CommonResponse.onSuccess(response,"펫 포스트 작성에 성공했습니다");
    }


    @PutMapping("/{petPostId}")
    public CommonResponse<PetPostEditResponse> editPetpost(
            @RequestBody PetPostEditRequest request,
            @PathVariable("petPostId") Long petPostId) throws ApplicationException{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        PetPostEditResponse response = petPostService.editPetpost(request, petPostId, email);
        return CommonResponse.onSuccess(response,"펫 포스트 수정에 성공했습니다.");
    }

    @GetMapping("/{petPostId}")
    public CommonResponse<PetPostDetailResponse> viewPetpost(
            @PathVariable("petPostId") Long petPostId) throws ApplicationException{

        PetPostDetailResponse response = petPostService.viewPetpost(petPostId);

        return CommonResponse.onSuccess(response,"펫 포스트 조회에 성공했습니다.");
    }

    @DeleteMapping("/{petPostId}")
    public CommonResponse<?> deletePetpost(
            @PathVariable("petPostId") Long petPostId) throws ApplicationException{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        petPostService.deletePetpost(petPostId, email);
        return CommonResponse.onSuccess(null, "포스트 삭제에 성공했습니다.");
    }

    @GetMapping("/doTest")
    public String doTest(){
        System.out.println("test");
        return "테스트 성공";
    }

}
