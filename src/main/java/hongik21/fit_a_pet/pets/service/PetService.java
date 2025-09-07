package hongik21.fit_a_pet.pets.service;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.pets.dto.PetInfo;
import hongik21.fit_a_pet.pets.dto.PetJoinRequest;
import hongik21.fit_a_pet.pets.entity.*;
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
                .age(request.getAge())
                .weight(request.getWeight())
                .image(request.getImage())
                .member(member)
                .createdAt(LocalDateTime.now())
                .build();

        // 1. petType
        if(request.getPetType() != null && !request.getPetType().isEmpty()) {
            String upperCase = request.getPetType().toUpperCase();
            pet.setType(PetType.valueOf(upperCase));
        }

        // 2. genderType
        if(request.getGenderType() != null && !request.getGenderType().isEmpty()) {
            String upperCase = request.getGenderType().toUpperCase();
            pet.setGender(PetGenderType.valueOf(upperCase));
        }

        // 3. 성향
        if (request.getTraitNames() != null && !request.getTraitNames().isEmpty()) {
            List<String> traitNames = request.getTraitNames();
            List<PetTrait> traits = petTraitRepository.findAllByNameIn(traitNames);

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
