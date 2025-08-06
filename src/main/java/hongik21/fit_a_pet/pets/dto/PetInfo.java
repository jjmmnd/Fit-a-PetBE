package hongik21.fit_a_pet.pets.dto;

import hongik21.fit_a_pet.pets.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetInfo {
    private Long id;
    private String name;
    private String image;
    private Long memberId;
    private List<String> traits;    // 성향 이름 리스트

    public static PetInfo from(Pet pet) {
        List<String> traitNames = pet.getTraits().stream()
                .map(rel -> rel.getTrait().getName())
                .toList();

        return new PetInfo(
                pet.getId(),
                pet.getName(),
                pet.getImage(),
                pet.getMember().getId(),
                traitNames
        );
    }
}
