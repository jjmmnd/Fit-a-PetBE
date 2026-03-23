Fit-a-Pet 서비스의 백엔드 레포지토리입니다.

## 📋 서비스 개요
> **반려인들을 위한 위치 기반 소셜 플랫폼**

- **프로젝트 기간**: 2025.08 ~ 2025.10 (3개월)
- **팀 구성**: 백엔드 1명, 프론트엔드/백엔드 1명

<br>

반려인들은 반려동물과 함께할 수 있는 장소나 활동 정보를 찾기 어렵고,  
비슷한 경험을 공유할 **소통 공간도 부족합니다.**  
**사용자 맞춤형**으로, **위치 기반** 서비스와 소셜 네트워크 기능을 결합하여  
실질적으로 반려인들의 삶을 더욱 풍요롭고 즐겁게 만들고자 합니다.

<br>

## 💻 기술 스택

### Backend
- Language: **Java 21**
- Framework: **Spring Boot 3.5**
- Database: **MySQL 8.0**
- Authentication: **Spring Security**
- Infrastructure: **AWS EC2, AWS RDS, AWS S3**
- etc: **Postman, Notion**

<br>

## 👨‍💻 담당 역할
[![GitHub](https://img.shields.io/badge/GitHub-jjmmnd-181717?style=flat-square&logo=github)](https://github.com/jjmmnd)
> **Backend Developer**

**주요 담당 업무**:
  - 회원관리 도메인 담당
  - AWS EC2를 활용한 백엔드 서버 배포
  - AWS S3를 활용한 이미지 파일 업로드/관리 시스템 구현

<br>

## ✨ 서비스 기능 소개

### 1. 정보 커뮤니티
**기능 설명**
- 반려동물 양육 정보, 건강 관리 팁, 훈련 노하우 등을 공유하는 커뮤니티
- 카테고리별 게시글 조회 및 검색 기능 제공
- 좋아요, 댓글을 통한 반려인 간 소통 활성화

**구현 내용**
- 게시글 CRUD API 설계 및 구현
- 카테고리 필터링 및 키워드 검색 기능 구현
- 좋아요/댓글 기능을 통한 사용자 인터랙션 구현

**사용 기술**
- Spring Data JPA, MySQL, AWS S3

---

### 2. 산책 기록 아카이빙
**기능 설명**
- 반려동물과의 산책 경로, 시간, 거리를 자동으로 기록
- 날짜별 산책 히스토리 조회 및 통계 제공

**구현 내용**
- GPS 좌표 기반 산책 경로 데이터 저장 및 조회 API 구현
- 산책 시간, 거리 계산 로직 개발
- 월별/주별 산책 통계 집계 기능 구현

**사용 기술**
- Spring Boot, JPA, MySQL

---


### 3. 회원 관리
**기능 설명**
- 이메일 인증 기반 일반 회원가입 지원
- 반려동물 프로필 등록 및 관리
- 마이페이지에서 내가 작성한 글, 산책 기록 등 통합 조회

**구현 내용**
- Spring Security + JWT 기반 인증/인가 시스템 구현
- SMTP를 활용한 이메일 인증 회원가입 기능 개발
- 이메일 인증 코드 발송 및 유효성 검증 로직 구현
- 반려동물 정보 등록 및 수정 API 개발
- 사용자별 활동 내역 조회 기능 구현

**사용 기술**
- Spring Security, JWT, Spring Mail (SMTP), JPA

<br>


## 🔗 링크
- **Frontend GitHub**: https://github.com/umhye1/Fit-a-PetFE