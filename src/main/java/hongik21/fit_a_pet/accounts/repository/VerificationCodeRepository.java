package hongik21.fit_a_pet.accounts.repository;

import hongik21.fit_a_pet.accounts.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    Optional<VerificationCode> findByEmailAndCode(String email, String code);
    void deletedByExpiryTimeBefore(LocalDateTime now);
}
