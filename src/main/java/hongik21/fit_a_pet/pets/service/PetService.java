package hongik21.fit_a_pet.pets.service;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.pets.dto.PetInfo;
import hongik21.fit_a_pet.pets.dto.PetJoinRequest;
import hongik21.fit_a_pet.pets.entity.Pet;
import hongik21.fit_a_pet.pets.entity.PetTrait;
import hongik21.fit_a_pet.pets.entity.PetTraitRelation;
import hongik21.fit_a_pet.pets.repository.PetRepository;
import hongik21.fit_a_pet.pets.repository.PetTraitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {

    private final PetRepository petRepository;
    private final PetTraitRepository petTraitRepository;

    /**
     * 펫 생성하기
     * @param member
     * @param request
     * @return
     */
    @Transactional
    public PetInfo createPet(Member member, PetJoinRequest request) {
        Pet pet = Pet.builder()
                .name(request.getName())
                .image(request.getImage())
                .member(member)
                .createdAt(LocalDateTime.now())
                .build();

        if (request.getTraitIds() != null && !request.getTraitIds().isEmpty()) {
            List<PetTrait> traits = petTraitRepository.findAllById(request.getTraitIds());

            for (PetTrait trait : traits) {
                PetTraitRelation relation = new PetTraitRelation();
                relation.setPet(pet);
                relation.setTrait(trait);
                pet.getTraits().add(relation);
            }
        }

        return PetInfo.from(petRepository.save(pet));
    }
    
    // 특정 회원의 펫 조회하기
    public List<PetInfo> getPetsByMember(Member member) {
        List<Pet> pets = petRepository.findByMember(member);
        return pets.stream()
                .map(PetInfo::from)
                .toList();
    }

    // 필요하면 알아서 추가 ...
}
