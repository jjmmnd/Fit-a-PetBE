package hongik21.fit_a_pet.accounts.service;

import hongik21.fit_a_pet.accounts.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;

    // 애플리케이션 실행 시점 (트랜잭션 무시되길래 일단 주석처리 ..
//    @EventListener(ApplicationReadyEvent.class)
//    public void onApplicationReady() {
//        deleteExpiredVerificationCodes();
//    }

    // 자정마다 만료 코드 삭제하는 스케줄러 (서비스 ing)
    @Transactional
    @Scheduled(cron = "0 0 12 * * ?")
    public void deleteExpiredVerificationCodes() {
        verificationCodeRepository.deleteByExpiryTimeBefore(LocalDateTime.now());
    }

}
