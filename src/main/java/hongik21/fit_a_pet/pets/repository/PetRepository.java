package hongik21.fit_a_pet.pets.repository;

import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.pets.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByMember(Member member);

    Pet getPetById(Long petId);
}
