package hongik21.fit_a_pet.pets;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.accounts.repository.MemberRepository;
import hongik21.fit_a_pet.pets.dto.PetInfo;
import hongik21.fit_a_pet.pets.dto.PetJoinRequest;
import hongik21.fit_a_pet.pets.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 테스트용으로 만든 컨트롤러
 */
@RestController
@RequestMapping("/api/test/pet")
@RequiredArgsConstructor
public class TestController {

    private final PetService petService;
    private final MemberRepository memberRepository;

    @PostMapping
    public ResponseEntity<PetInfo> createPet(@RequestBody PetJoinRequest request) {
        // 테스트용: 1번 멤버
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        PetInfo petInfo = petService.createPet(member, request);
        return ResponseEntity.ok(petInfo);
    }

    /**
     * 회원의 모든 펫 조회 API
     */
    @GetMapping
    public ResponseEntity<List<PetInfo>> getPets() {
        // 테스트용: 1번 멤버
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<PetInfo> pets = petService.getPetsByMember(member);
        return ResponseEntity.ok(pets);
    }

}
