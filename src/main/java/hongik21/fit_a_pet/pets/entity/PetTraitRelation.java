package hongik21.fit_a_pet.pets.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
}
