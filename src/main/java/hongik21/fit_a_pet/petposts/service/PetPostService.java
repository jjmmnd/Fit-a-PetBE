package hongik21.fit_a_pet.petposts.service;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.accounts.repository.MemberRepository;
import hongik21.fit_a_pet.global.exception.ApplicationException;
import hongik21.fit_a_pet.global.exception.CustomErrorCode;
import hongik21.fit_a_pet.petposts.domain.PetPost;
import hongik21.fit_a_pet.petposts.domain.PetPostCategoryType;
import hongik21.fit_a_pet.petposts.dto.PetPostEditRequest;
import hongik21.fit_a_pet.petposts.dto.PetPostEditResponse;
import hongik21.fit_a_pet.petposts.dto.PetPostWriteRequest;
import hongik21.fit_a_pet.petposts.dto.PetPostWriteResponse;
import hongik21.fit_a_pet.petposts.repository.PetPostRepository;
import hongik21.fit_a_pet.pets.entity.Pet;
import hongik21.fit_a_pet.pets.repository.PetRepository;
import hongik21.fit_a_pet.posts.dto.PostEditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PetPostService {

    private final PetPostRepository petPostRepository;
    private final PetRepository petRepository;
    private final MemberRepository memberRepository;

    // 1. 게시글 작성
    public PetPostWriteResponse petWrite(String email, PetPostWriteRequest request){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new ApplicationException(CustomErrorCode.PET_POST_NOT_FOUND));

//        Pet pet = petRepository.findById(request.getPetId())
//                .orElseThrow(() -> new ApplicationException(CustomErrorCode.PET_POST_NOT_FOUND)); // pet 에러코드없어서 임시로 둠

        PetPost post = PetPost.builder()
                .member(member)
//                .pet(pet)
                .petPostTitle(request.getPetPostTitle())
                .petPostContent(request.getPetPostContent())
                .petPostCategory(request.getPetPostCategory())
                .petPostDate(request.getPetPostDate())
                .build();
        try{
            PetPost res = petPostRepository.save(post);
            return PetPostWriteResponse.builder()
                    .petPostId(res.getPetPostId())
                    .petPostTitle(res.getPetPostTitle())
                    .petPostContent(res.getPetPostContent())
                    .petPostCategory(res.getPetPostCategory())
                    .petPostDate(res.getPetPostDate())
                    .nickname(res.getMember().getNickname())
//                    .petName(res.getPet().getName())
                    .build();
        } catch (Exception e) {
            throw new ApplicationException(CustomErrorCode.PET_POST_NOT_FOUND);
        }
    }

    // 2. 수정
    public PetPostEditResponse petEdit(PetPostEditRequest request, Long petPostId, String email){
        PetPost post = petPostRepository.findByPetPostIdAndMemberId_Email(petPostId, email)
                .orElseThrow(()-> new ApplicationException(CustomErrorCode.PET_POST_NOT_FOUND));

        post.setPetPostTitle(request.getPetPostTitle());
        post.setPetPostContent(request.getPetPostContent());
        post.setPetPostEditDate(request.getPetPostEditDate());

        PetPost updatedPost = petPostRepository.save(post);

        try{
            return PetPostEditResponse.builder()
                    .petPostId(updatedPost.getPetPostId())
                    .petPostTitle(updatedPost.getPetPostTitle())
                    .petPostCategory(updatedPost.getPetPostCategory())
                    .petPostContent(updatedPost.getPetPostContent())
                    .petPostDate(updatedPost.getPetPostEditDate())
                    .petPostEditDate(updatedPost.getPetPostEditDate())
                    .nickname(updatedPost.getMember().getNickname())
//                    .petName(updatedPost.getPet().getName())
                    .build();
        } catch (Exception e) {
            throw new ApplicationException(CustomErrorCode.PET_POST_NOT_FOUND);
        }
    }

}
