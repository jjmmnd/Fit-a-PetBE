package hongik21.fit_a_pet.pets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PetJoinRequest {
    private String name;
    private String image;
    private List<Long> traitIds;
}
