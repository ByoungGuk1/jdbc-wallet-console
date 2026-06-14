package com.app.wallet.query;

public class MemberQuery {

	public static final String INSERT_MEMBER = """
			INSERT INTO TBL_MEMBER
			VALUES (SEQ_MEMBER.NEXTVAL, ?, ?, ?, 'USER', SYSDATE, NULL)
			""";

	public static final String SELECT_MEMBER_BY_EMAIL = """
			SELECT ID, MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_NICKNAME, MEMBER_ROLE, CREATED_AT, UPDATED_AT
			FROM TBL_MEMBER
			WHERE MEMBER_EMAIL = ?
			""";

	public static final String SELECT_MEMBER_BY_ID = """
			SELECT ID, MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_NICKNAME, MEMBER_ROLE, CREATED_AT, UPDATED_AT
			FROM TBL_MEMBER
			WHERE ID = ?
			""";

	public static final String UPDATE_MEMBER_NICKNAME = """
			UPDATE TBL_MEMBER
			SET MEMBER_NICKNAME = ?,
			    UPDATED_AT = SYSDATE
			WHERE ID = ?
			""";
}
