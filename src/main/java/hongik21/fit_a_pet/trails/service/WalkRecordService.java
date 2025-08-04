package hongik21.fit_a_pet.trails.service;


import hongik21.fit_a_pet.accounts.entity.Member;
import hongik21.fit_a_pet.accounts.repository.MemberRepository;
import hongik21.fit_a_pet.global.exception.ApplicationException;
import hongik21.fit_a_pet.global.exception.CustomErrorCode;
import hongik21.fit_a_pet.trails.domain.WalkRecord;
import hongik21.fit_a_pet.trails.dto.WalkRecordRequestDTO;
import hongik21.fit_a_pet.trails.dto.WalkRecordResponseDTO;
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
    public WalkRecordResponseDTO register(String email, WalkRecordRequestDTO request) {
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
                .build();

        try {
            WalkRecord res = repository.save(walkRecord);
            return WalkRecordResponseDTO.builder()
                    .recordId(res.getRecordId())
                    .walkDate(res.getWalkDate().toString())
                    .walkStart(res.getWalkStart().toString())
                    .walkEnd(res.getWalkEnd().toString())
                    .distance(res.getDistance())
                    .petId(res.getPetId())
                    .address(res.getAddress()).build();
        } catch (Exception e) {
            throw new ApplicationException(CustomErrorCode.TRAIL_CREATE_FAILED);
        }
    }

//
//    // 기능 2. 월별 기록 조회
//    public List<WalkRecordResponseDTO> getMonthlyRecords(int year, int month){
//
//        LocalDate startDate = LocalDate.of(year, month, 1);
//        LocalDate endDate = LocalDate.of(year, month, 31);
//
//        // statDate,endDate 사이의 walkRecord를 가져옴
//        return repository.findByWalkDateBetween(startDate, endDate)
//                .stream()
//                .map(r ->  new WalkRecordResponseDTO(  // 각 walkRecord 객체 r을 WalkRecordResponseDTO 객체로 변환
//                        r.getRecordId(),
//                        r.getWalkDate().toString(),
//                        r.getWalkStart().toString(),
//                        r.getWalkEnd().toString(),
//                        r.getDistance(),
//                        r.getRating(),
//                        r.getMemo(),
//                        r.getPetId(),
//                        r.getUserId(),
//                        r.getAddress()
//                )) // List<WalkRecord> -> 스트림형태
//                .collect(Collectors.toList()); // List<WalkRecordResponseDto> 형태로 변환
//    }

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
