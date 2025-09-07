package hongik21.fit_a_pet.pets.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PetTraitRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trait_id")
    private PetTrait trait;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetTraitRelation that)) return false;

        return pet != null && trait != null &&
                pet.getId() != null && trait.getId() != null &&
                pet.getId().equals(that.pet.getId()) &&
                trait.getId().equals(that.trait.getId());
    }

    @Override
    public int hashCode() {
        int result = pet != null && pet.getId() != null ? pet.getId().hashCode() : 0;
        result = 31 * result + (trait != null && trait.getId() != null ? trait.getId().hashCode() : 0);
        return result;
    }
}
