# JDBC Wallet Console

Oracle JDBC 기반 개인 지갑 관리 콘솔 프로그램입니다.

## 주요 기능

- 회원가입 / 로그인
- 계좌 CRUD
- 카테고리 CRUD
- 수입/지출 거래 CRUD
- 거래 등록/수정/삭제 시 계좌 잔액 반영
- 총 자산 조회
- 계좌별 잔액 조회
- 월별 수입/지출 조회
- 카테고리별 조회

## 테이블

- TBL_MEMBER
- TBL_ACCOUNT
- TBL_CATEGORY
- TBL_TRANSACTION

## 실행 순서

1. `database/schema.sql` 실행
2. `database/initdummy.sql` 실행
3. `com.app.wallet.app.App` 실행

## DB 연결

`DBUtil.java`에서 Oracle 21c Docker 환경 기준 URL을 사용합니다.

```java
jdbc:oracle:thin:@//localhost:1522/XEPDB1
```

## 구조

```text
App
→ Controller
→ Service
→ DAO
→ Query
→ DBUtil
→ Oracle DB
```

## 패키지별 역할

| 패키지     | 역할                                           |
| ---------- | ---------------------------------------------- |
| app        | 프로그램 실행 진입점                           |
| controller | 메뉴 흐름 제어, View 입력값 수신, Service 호출 |
| view       | 콘솔 입력/출력 담당                            |
| service    | 기능 로직 처리, 검증, 잔액 반영                |
| dao        | JDBC로 DB 접근                                 |
| query      | SQL문 상수 관리                                |
| dto        | 계층 간 데이터 전달 객체                       |
| util       | DB 연결/해제 공통 처리                         |

## 트랜잭션 처리

거래 등록  
→ 거래 저장  
→ 계좌 잔액 증가/감소

거래 수정  
→ 기존 거래 잔액 되돌림  
→ 새 거래 내용 반영  
→ 계좌 잔액 재계산

거래 삭제  
→ 기존 거래 잔액 되돌림  
→ 거래 삭제
