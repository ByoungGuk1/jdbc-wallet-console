package com.app.wallet.query;

public class CategoryQuery {

	private CategoryQuery() {
	}

	// 카테고리 등록
	// MEMBER_ID, CATEGORY_NAME, CATEGORY_TYPE
	public static final String INSERT_CATEGORY = """
			INSERT INTO TBL_CATEGORY
			VALUES (SEQ_CATEGORY.NEXTVAL, ?, ?, ?, SYSDATE, NULL)
			""";

	// 내 카테고리 목록 조회
	// MEMBER_ID
	public static final String SELECT_CATEGORIES_BY_MEMBER_ID = """
			SELECT ID, MEMBER_ID, CATEGORY_NAME, CATEGORY_TYPE, CREATED_AT, UPDATED_AT
			FROM TBL_CATEGORY
			WHERE MEMBER_ID = ?
			ORDER BY CATEGORY_TYPE, CATEGORY_NAME
			""";

	// 수입 지출별 카테고리 조회
	// MEMBER_ID, CATEGORY_TYPE
	public static final String SELECT_CATEGORIES_BY_TYPE = """
			SELECT ID, MEMBER_ID, CATEGORY_NAME, CATEGORY_TYPE
			FROM TBL_CATEGORY
			WHERE MEMBER_ID = ?
			AND CATEGORY_TYPE = ?
			ORDER BY CATEGORY_NAME
			""";

	// 특정 카테고리 조회
	// CATEGORY_ID, MEMBER_ID
	public static final String SELECT_CATEGORY_BY_ID = """
			SELECT ID, MEMBER_ID, CATEGORY_NAME, CATEGORY_TYPE
			FROM TBL_CATEGORY
			WHERE ID = ?
			AND MEMBER_ID = ?
			""";

	// 카테고리 수정
	// CATEGORY_NAME, CATEGORY_TYPE, CATEGORY_ID, MEMBER_ID
	public static final String UPDATE_CATEGORY = """
			UPDATE TBL_CATEGORY
			SET CATEGORY_NAME = ?,
			    CATEGORY_TYPE = ?,
			    UPDATED_AT = SYSDATE
			WHERE ID = ?
			AND MEMBER_ID = ?
			""";

	// 카테고리 삭제
	// CATEGORY_ID, MEMBER_ID
	public static final String DELETE_CATEGORY = """
			DELETE FROM TBL_CATEGORY
			WHERE ID = ?
			AND MEMBER_ID = ?
			""";
}