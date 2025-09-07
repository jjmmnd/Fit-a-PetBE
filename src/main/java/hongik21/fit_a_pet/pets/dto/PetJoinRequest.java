package hongik21.fit_a_pet.pets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class PetJoinRequest {
    private String name;
    private Integer age;
    private Double weight;
    private String genderType;
    private String petType;
    private String image;
    private List<String> traitNames;
}
