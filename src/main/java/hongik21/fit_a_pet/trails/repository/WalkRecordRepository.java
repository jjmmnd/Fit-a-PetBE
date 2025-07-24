package hongik21.fit_a_pet.trails.repository;

import hongik21.fit_a_pet.trails.domain.WalkRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WalkRecordRepository extends JpaRepository<WalkRecord, Integer> {

    // 날짜 기준 월별 조회
    List<WalkRecord> findByWalkMonth(LocalDate startDate, LocalDate endDate);

    // 특정 날짜 조회
    List<WalkRecord> findByWalkDate(LocalDate date);

}
