package com.app.wallet.query;

public class CategoryQuery {

	public static final String INSERT_CATEGORY = """
			INSERT INTO TBL_CATEGORY
			VALUES (SEQ_CATEGORY.NEXTVAL, ?, ?, ?, SYSDATE, NULL)
			""";

	public static final String SELECT_CATEGORIES_BY_MEMBER_ID = """
			SELECT ID, MEMBER_ID, CATEGORY_NAME, CATEGORY_TYPE, CREATED_AT, UPDATED_AT
			FROM TBL_CATEGORY
			WHERE MEMBER_ID = ?
			ORDER BY ID
			""";

	public static final String SELECT_CATEGORIES_BY_TYPE = """
			SELECT ID, MEMBER_ID, CATEGORY_NAME, CATEGORY_TYPE, CREATED_AT, UPDATED_AT
			FROM TBL_CATEGORY
			WHERE MEMBER_ID = ?
			  AND CATEGORY_TYPE = ?
			ORDER BY ID
			""";

	public static final String SELECT_CATEGORY_BY_ID = """
			SELECT ID, MEMBER_ID, CATEGORY_NAME, CATEGORY_TYPE, CREATED_AT, UPDATED_AT
			FROM TBL_CATEGORY
			WHERE ID = ?
			  AND MEMBER_ID = ?
			ORDER BY ID
			""";

	public static final String UPDATE_CATEGORY = """
			UPDATE TBL_CATEGORY
			SET CATEGORY_NAME = ?,
			    CATEGORY_TYPE = ?,
			    UPDATED_AT = SYSDATE
			WHERE ID = ?
			  AND MEMBER_ID = ?
			""";

	public static final String DELETE_CATEGORY = """
			DELETE FROM TBL_CATEGORY
			WHERE ID = ?
			  AND MEMBER_ID = ?
			""";
}
