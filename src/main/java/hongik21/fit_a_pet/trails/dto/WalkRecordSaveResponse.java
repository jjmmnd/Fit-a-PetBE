package hongik21.fit_a_pet.trails.dto;

import lombok.*;

// 프론트 -> 백엔드로 산책 기록 저장 요청을 보낼 때 사용
// 응답 DTO는 우리가 new DTO( )로 직접 만들기 때문에 @AllArgsConstructor로 생성자 자동 생성이 편리
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkRecordSaveResponse {

    private Long recordId;
    // 문자열로 받아서 LocalTime, LocalDate로 변경하는게 일반적이라고 함
    private String walkDate;
    private String walkStart;
    private String walkEnd;

    private Float distance;
    private Integer petId;
    private String address;

    private Integer rating;
    private String memo;

}
