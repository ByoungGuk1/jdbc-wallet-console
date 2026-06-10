package com.app.wallet.query;

public class MemberQuery {

	private MemberQuery() {
	}

	// 회원가입
	// MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_NICKNAME
	public static final String INSERT_MEMBER = """
			INSERT INTO TBL_MEMBER
			VALUES (SEQ_MEMBER.NEXTVAL, ?, ?, ?, 'USER', SYSDATE, NULL)
			""";

	// 로그인 조회
	// MEMBER_EMAIL
	public static final String SELECT_MEMBER_BY_EMAIL = """
			SELECT ID, MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_NICKNAME, MEMBER_ROLE
			FROM TBL_MEMBER
			WHERE MEMBER_EMAIL = ?
			""";

	// 회원 정보 조회
	// MEMBER_ID
	public static final String SELECT_MEMBER_BY_ID = """
			SELECT ID, MEMBER_EMAIL, MEMBER_NICKNAME, MEMBER_ROLE, CREATED_AT, UPDATED_AT
			FROM TBL_MEMBER
			WHERE ID = ?
			""";

	// 회원 닉네임 수정
	// MEMBER_NICKNAME, MEMBER_ID
	public static final String UPDATE_MEMBER_NICKNAME = """
			UPDATE TBL_MEMBER
			SET MEMBER_NICKNAME = ?,
			    UPDATED_AT = SYSDATE
			WHERE ID = ?
			""";

	// 비밀번호 수정
	// MEMBER_PASSWORD, MEMBER_ID
	public static final String UPDATE_MEMBER_PASSWORD = """
			UPDATE TBL_MEMBER
			SET MEMBER_PASSWORD = ?,
			    UPDATED_AT = SYSDATE
			WHERE ID = ?
			""";
}