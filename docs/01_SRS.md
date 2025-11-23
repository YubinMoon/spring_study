# Backend Community SRS

## 1. Introduction

### 1.1 Purpose

커뮤니티 백엔드의 구성과 기능을 정의하는 문서이다.
문서를 간결하게 유지하며 지속적으로 필요한 기능을 추가 할 예정이다.

### 1.2 Scope

이 백엔드 시스템은 커뮤니티 애플리케이션의 핵심 기능을 제공한다.

## 2. Overall Description

### 2.1 System Context

이 시스템은 컨테이너 환경에서 실행되며 MariaDB를 데이터 저장소로 사용한다.

### 2.2 Constraints

- RESTful API 아키텍처 준수
- JSON 데이터 포맷 사용
- 세션 인증 사용
- RDBMS 사용

## 3. Specific Requirements

### 3.1 Functional Requirements (FR)

#### 3.1.1 User Management

**FR-001 회원가입**

- 입력: username, password, nickname
- 처리:
  1. username 중복 검증
  2. password 정책 검증
  3. 닉네임 검증
  4. 비밀번호 해시 처리
- 출력: userId
- 예외: USER_ID_DUPLICATE, INVALID_PASSWORD

**FR-002 로그인**

- 입력: username, password
- 처리: 사용자 인증 후 세션 쿠키 발급
- 출력: session Cookie
- 예외: INVALID_CREDENTIALS

**FR-003 회원 정보 조회**

- 입력: userId
- 처리: 유저 정보 조회
- 출력: nickname, createdAt
- 예외: USER_NOT_FOUND

**FR-004 회원 정보 수정**
- 입력: nickname
- 처리:
  1. 세션으로 유저 인증
  2. 닉네임 검증
  3. 닉네임 수정
- 출력: 성공 여부
- 예외: UNAUTHORIZED, INVALID_NICKNAME

**FR-005 회원 탈퇴**

- 입력: userId
- 처리:
  1. 유저 세션 검증
  2. 소프트 삭제 처리
- 출력: 성공 여부
- 예외: UNAUTHORIZED, USER_NOT_FOUND

#### 3.1.2 Post Management

**FR-010 게시글 작성**

- 입력: title, content
- 처리: 게시글 생성
- 출력: postId
- 예외: INVALID_TITLE, UNAUTHORIZED

**FR-011 게시글 조회**

- 입력: postId
- 처리: 개시글 조회
- 출력: title, content, author, createdAt, likeCount, unlikeCount, comments[]
- 예외: POST_NOT_FOUND

**FR-012 게시글 목록 조회**

- 입력: page, size
- 처리: 최신 게시글 기준 페이징 조회 
- 출력: posts[]
- 예외: 없음

**FR-013 게시글 수정**

- 입력: postId, title, content
- 처리:
  1. 세션으로 작성자 확인
  2. 게시글 수정
- 출력: 성공 여부
- 예외: UNAUTHORIZED, POST_NOT_FOUND

**FR-014 게시글 삭제**

- 입력: postId
- 처리: 
  1. 세션으로 작성자 확인
  2. 소프트 삭제 처리
- 출력: 성공 여부
- 예외: UNAUTHORIZED, POST_NOT_FOUND

#### 3.1.3 Comment Management

**FR-020 댓글 작성**

- 입력: postId, content
- 처리:
  1. 유저 세션 검증
  2. 개시글 존재 여부 검증
  3. 댓글 생성
- 출력: commentId
- 예외: UNAUTHORIZED, POST_NOT_FOUND

**FR-021 댓글 목록 조회**

- 입력: postId
- 처리: 게시글의 댓글 조회
- 출력: comments[]
- 예외: 없음

**FR-022 댓글 수정**
- 입력: commentId, content
- 처리:
  1. 유저 세션 검증
  2. 댓글 작성자 확인
  3. 댓글 수정
- 출력: 성공 여부
- 예외: UNAUTHORIZED, COMMENT_NOT_FOUND

**FR-023 댓글 삭제**

- 입력: commentId
- 처리:
  1. 유저 세션 검증
  2. 댓글 작성자 확인
  3. 소프트 삭제 처리
- 출력: 성공 여부
- 예외: UNAUTHORIZED, COMMENT_NOT_FOUND

#### 3.1.4 Like Management

**FR-030 좋아요 등록/해제**

- 입력: postId
- 처리:
  1. 유저 세션 검증
  2. 게시글 존재 여부 검증
  3. 좋아요 토글 처리
- 출력: 현재 좋아요 상태 (liked: true/false)
- 예외: UNAUTHORIZED, POST_NOT_FOUND

### 3.3 Non-Functional Requirements (NFR)

**NFR-001 성능**

- 모든 API 응답 시간 200ms 이내
- 동시 사용자 1000명 처리 가능

**NFR-002 보안**

- 비밀번호 솔트 및 해시 저장
- 세션 기반 인증

### 3.4 Data Requirements

#### 3.4.1 ERD (요구사항 수준 기술)

- User (1) - (N) Post
- User (1) - (N) Comment
- Post (1) - (N) Comment
- Post (1) - (N) Like
- User (1) - (N) Like

#### 3.4.2 Validation Rules

- password: 최소 8자
- nickname: 2~20자
- title: 1~100자
