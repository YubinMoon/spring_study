# Community Backend API Specification

## 0. Common

### 0.1. 공통 에러 응답

```json
{
  "code": "string",
  "message": "string"
}
```

## 1. User Management

### 1.1. 회원가입 (FR-001)

- method: POST
- URL: /api/v1/users/register
- Auth: None

**요청**
```json
{
  "username": "string",
  "password": "string",
  "nickname": "string"
}
```

**검증**
- username: 중복 불가 (USER_ID_DUPLICATE)
- password: 8자 이상 (INVALID_PASSWORD)
- nickname: 2~20자 (INVALID_NICKNAME)

**성공 응답 201**
```json
{
  "userId": "int"
}
```

**실패 응답**

- USER_ID_DUPLICATE (400)
- INVALID_PASSWORD (400)
- INVALID_NICKNAME (400)

### 1.2. 로그인 (FR-002)

- method: POST
- URL: /api/v1/users/login
- Auth: None

**요청**
```json
{
  "username": "string",
  "password": "string"
}
```

**검증**
- username: 존재하지 않음 (INVALID_CREDENTIALS)
- password: 일치하지 않음 (INVALID_CREDENTIALS)

**성공 응답 200**
```json
{
  "userId": "int"
}
```

**실패 응답**

- INVALID_CREDENTIALS (400)

### 1.3. 회원 정보 조회 (FR-003)

- method: GET
- URL: /api/v1/users/{userId}
- Auth: None

**검증**
- userId: 존재하지 않음 (USER_NOT_FOUND)

**성공 응답 200**
```json
{
  "userId": "int",
  "nickname": "string",
  "createdAt": "string"
}
```

**실패 응답**

- USER_NOT_FOUND (400)

### 1.4. 회원 정보 수정 (FR-004)

- method: PUT
- URL: /api/v1/users/me
- Auth: Session

**요청**
```json
{
  "nickname": "string"
}
```

**검증**
- 로그인 인증 (UNAUTHORIZED)
- nickname: 2~20자 (INVALID_NICKNAME)

**성공 응답 204**

**실패 응답**

- UNAUTHORIZED (401)
- INVALID_NICKNAME (400)

### 1.5. 회원 탈퇴 (FR-005)

- method: DELETE
- URL: /api/v1/users/me
- Auth: Session

**검증**
- 로그인 인증 (UNAUTHORIZED)

**성공 응답 204**

**실패 응답**

- UNAUTHORIZED (401)

## 2. Post Management

### 2.1. 게시글 작성 (FR-010)

- method: POST
- URL: /api/v1/posts
- Auth: Session

**요청**
```json
{
  "title": "string",
  "content": "string"
}
```

**검증**
- 로그인 인증 (UNAUTHORIZED)
- title: 2~100자 (INVALID_TITLE)

**성공 응답 201**
```json
{
  "postId": "int"
}
```

**실패 응답**

- UNAUTHORIZED (401)
- INVALID_TITLE (400)

### 2.2. 게시글 조회 (FR-011)

- method: GET
- URL: /api/v1/posts/{postId}
- Auth: None

**검증**
- postId: 존재하지 않음 (POST_NOT_FOUND)

**성공 응답 200**
```json
{
  "postId": "int",
  "title": "string",
  "content": "string",
  "author": {
    "userId": "int",
    "nickname": "string"
  },
  "createdAt": "string",
  "likeCount": "int",
  "unlikeCount": "int",
  "comments": [
    {
      "commentId": "int",
      "content": "string",
      "author": {
        "userId": "int",
        "nickname": "string"
      },
      "createdAt": "string"
    }
  ]
}
```

**실패 응답**

- POST_NOT_FOUND (400)

### 2.3. 게시글 목록 조회(FR-012)

- method: GET
- URL: /api/v1/posts?page={page}&size={size}
- Auth: None

**성공 응답 200**
```json
[
  {
    "postId": "int",
    "title": "string",
    "author": {
      "userId": "int",
      "nickname": "string"
    },
    "createdAt": "string",
    "likeCount": "int",
    "unlikeCount": "int"
  }
]
```

### 2.4. 게시글 수정 (FR-013)

- method: PUT
- URL: /api/v1/posts/{postId}
- Auth: Session

**요청**
```json
{
  "title": "string",
  "content": "string"
}
```

**검증**
- 로그인 인증 (UNAUTHORIZED)
- postId: 존재하지 않음 (POST_NOT_FOUND)
- title: 2~100자 (INVALID_TITLE)

**성공 응답 204**

**실패 응답**

- UNAUTHORIZED (401)
- POST_NOT_FOUND (400)
- INVALID_TITLE (400)

### 2.5. 게시글 삭제 (FR-014)

- method: DELETE
- URL: /api/v1/posts/{postId}
- Auth: Session

**검증**
- 로그인 인증 (UNAUTHORIZED)
- postId: 존재하지 않음 (POST_NOT_FOUND)

**성공 응답 204**

**실패 응답**

- UNAUTHORIZED (401)
- POST_NOT_FOUND (400)

## 3. Comment Management

### 3.1. 댓글 작성 (FR-020)

- method: POST
- URL: /api/v1/comments
- Auth: Session

**요청**
```json
{
  "postId": "int",
  "content": "string"
}
```

**검증**
- 로그인 인증 (UNAUTHORIZED)
- postId: 존재하지 않음 (POST_NOT_FOUND)

**성공 응답 201**
```json
{
  "commentId": "int"
}
```

**실패 응답**

- UNAUTHORIZED (401)
- POST_NOT_FOUND (400)

### 3.2. 댓글 목록 조회 (FR-021)

- method: GET
- URL: /api/v1/comments?postId={postId}
- Auth: None

**성공 응답 200**
```json
[
  {
    "commentId": "int",
    "content": "string",
    "author": {
      "userId": "int",
      "nickname": "string"
    },
    "createdAt": "string"
  }
]
```

**실패 응답**

- POST_NOT_FOUND (400)

### 3.3. 댓글 수정 (FR-022)

- method: PUT
- URL: /api/v1/comments/{commentId}
- Auth: Session

**요청**
```json
{
  "content": "string"
}
```

**검증**
- 로그인 인증 (UNAUTHORIZED)
- commentId: 존재하지 않음 (COMMENT_NOT_FOUND)

**성공 응답 204**

**실패 응답**

- UNAUTHORIZED (401)
- COMMENT_NOT_FOUND (400)

### 3.4. 댓글 삭제 (FR-023)

- method: DELETE
- URL: /api/v1/comments/{commentId}
- Auth: Session

**검증**
- 로그인 인증 (UNAUTHORIZED)
- commentId: 존재하지 않음 (COMMENT_NOT_FOUND)

**성공 응답 204**

**실패 응답**

- UNAUTHORIZED (401)
- COMMENT_NOT_FOUND (400)

## 4. Like Management

### 4.1. 좋아요 등록/해제 (FR-030)

- method: POST
- URL: /api/v1/likes
- Auth: Session

**요청**
```json
{
  "postId": "int"
}
```

**검증**
- 로그인 인증 (UNAUTHORIZED)
- postId: 존재하지 않음 (POST_NOT_FOUND)

**성공 응답 200**
```json
{
  "liked": "boolean"
}
```

**실패 응답**

- UNAUTHORIZED (401)
- POST_NOT_FOUND (400)
