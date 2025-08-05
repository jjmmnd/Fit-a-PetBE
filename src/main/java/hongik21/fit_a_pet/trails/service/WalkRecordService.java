package hongik21.fit_a_pet.trails.service;


import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.accounts.repository.MemberRepository;
import hongik21.fit_a_pet.global.exception.ApplicationException;
import hongik21.fit_a_pet.global.exception.CustomErrorCode;
import hongik21.fit_a_pet.trails.domain.WalkRecord;
import hongik21.fit_a_pet.trails.dto.WalkRecordMonthlyResponse;
import hongik21.fit_a_pet.trails.dto.WalkRecordSaveRequest;
import hongik21.fit_a_pet.trails.dto.WalkRecordSaveResponse;
import hongik21.fit_a_pet.trails.repository.WalkRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalkRecordService {

    // 생성자
    private final WalkRecordRepository repository;
    private final MemberRepository memberRepository;

    // 기능 1. 산책 기록 저장
    public WalkRecordSaveResponse register(String email, WalkRecordSaveRequest request) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new ApplicationException(CustomErrorCode.MEMBER_NOT_FOUND));

        WalkRecord walkRecord = WalkRecord.builder()
                .memberId(member)
                .petId(request.getPetId())
                .walkDate(LocalDate.parse(request.getWalkDate()))
                .walkStart(LocalTime.parse(request.getWalkStart()))
                .walkEnd(LocalTime.parse(request.getWalkEnd()))
                .distance(request.getDistance())
                .address(request.getAddress())
                .memo(request.getMemo())
                .rating(request.getRating())
                .build();

        try {
            WalkRecord res = repository.save(walkRecord);
            return WalkRecordSaveResponse.builder()
                    .recordId(res.getRecordId())
                    .walkDate(res.getWalkDate().toString())
                    .walkStart(res.getWalkStart().toString())
                    .walkEnd(res.getWalkEnd().toString())
                    .distance(res.getDistance())
                    .petId(res.getPetId())
                    .address(res.getAddress())
                    .memo(res.getMemo())
                    .rating(res.getRating())
                    .build();
        } catch (Exception e) {
            throw new ApplicationException(CustomErrorCode.TRAIL_CREATE_FAILED);
        }
    }

    // 기능 2. 월별 기록 조회

    public List<WalkRecordMonthlyResponse> getMonthlyRecords(String email, int year, int month){

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.of(year, month, 31);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new ApplicationException(CustomErrorCode.MEMBER_NOT_FOUND));

        try{
            return repository.findByWalkDateBetween(startDate, endDate)
                    .stream()
                    .map(r -> new WalkRecordMonthlyResponse(
                            r.getRecordId(),
                            r.getWalkDate().toString(),
                            r.getWalkStart().toString(),
                            r.getWalkEnd().toString(),
                            r.getDistance(),
                            r.getPetId(),
                            r.getAddress(),
                            r.getRating(),
                            r.getMemo()
                    ))
                    .toList();
        } catch (Exception e) {
            throw new ApplicationException(CustomErrorCode.TRAIL_NOT_FOUND);
        }
    }

    // 기능 3. 날짜별 기록 조회
//    public List<WalkRecordResponseDTO> getDateRecords(String dateStr){
//        LocalDate date = LocalDate.parse(dateStr);
//        return repository.findByWalkDate(date)
//                .stream()
//                .map(r -> new WalkRecordResponseDTO(
//                        r.getRecordId(),
//                        r.getWalkDate().toString(),
//                        r.getWalkStart().toString(),
//                        r.getWalkEnd().toString(),
//                        r.getDistance(),
//                        r.getRating(),
//                        r.getMemo(),
//                        r.getPetId(),
//                        r.getAddress()
//                ))
//                .collect(Collectors.toList());
//    }


    // 기능 4. 산책 기록 편집
}
