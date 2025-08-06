package hongik21.fit_a_pet.pets.repository;

import hongik21.fit_a_pet.pets.entity.PetTrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTraitRepository extends JpaRepository<PetTrait, Long> {
}
