package com.app.wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.wallet.dto.CategoryDTO;
import com.app.wallet.query.CategoryQuery;
import com.app.wallet.util.DBUtil;

public class CategoryDAO {

	public int insertCategory(CategoryDTO category) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(CategoryQuery.INSERT_CATEGORY);

			pst.setInt(1, category.getMemberId());
			pst.setString(2, category.getCategoryName());
			pst.setString(3, category.getCategoryType());

			return pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("카테고리 등록 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}
	}

	public List<CategoryDTO> selectCategoriesByMemberId(int memberId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<CategoryDTO> categories = new ArrayList<>();

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(CategoryQuery.SELECT_CATEGORIES_BY_MEMBER_ID);
			pst.setInt(1, memberId);
			rs = pst.executeQuery();

			while (rs.next()) {
				categories.add(makeCategory(rs));
			}

			return categories;
		} catch (SQLException e) {
			throw new RuntimeException("카테고리 목록 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public List<CategoryDTO> selectCategoriesByType(CategoryDTO condition) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<CategoryDTO> categories = new ArrayList<>();

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(CategoryQuery.SELECT_CATEGORIES_BY_TYPE);
			pst.setInt(1, condition.getMemberId());
			pst.setString(2, condition.getCategoryType());
			rs = pst.executeQuery();

			while (rs.next()) {
				categories.add(makeCategory(rs));
			}

			return categories;
		} catch (SQLException e) {
			throw new RuntimeException("유형별 카테고리 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public CategoryDTO selectCategoryById(CategoryDTO condition) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(CategoryQuery.SELECT_CATEGORY_BY_ID);
			pst.setInt(1, condition.getId());
			pst.setInt(2, condition.getMemberId());
			rs = pst.executeQuery();

			if (rs.next()) {
				return makeCategory(rs);
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException("카테고리 상세 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public CategoryDTO selectCategoryById(Connection conn, CategoryDTO condition) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement(CategoryQuery.SELECT_CATEGORY_BY_ID);
			pst.setInt(1, condition.getId());
			pst.setInt(2, condition.getMemberId());
			rs = pst.executeQuery();

			if (rs.next()) {
				return makeCategory(rs);
			}

			return null;
		} finally {
			DBUtil.dbDisconnect(null, pst, rs);
		}
	}

	public int updateCategory(CategoryDTO category) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(CategoryQuery.UPDATE_CATEGORY);

			pst.setString(1, category.getCategoryName());
			pst.setString(2, category.getCategoryType());
			pst.setInt(3, category.getId());
			pst.setInt(4, category.getMemberId());

			return pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("카테고리 수정 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}
	}

	public int deleteCategory(CategoryDTO condition) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(CategoryQuery.DELETE_CATEGORY);

			pst.setInt(1, condition.getId());
			pst.setInt(2, condition.getMemberId());

			return pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("카테고리 삭제 중 오류가 발생했습니다. 거래에 사용된 카테고리는 삭제할 수 없습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}
	}

	private CategoryDTO makeCategory(ResultSet rs) throws SQLException {
		return CategoryDTO.builder().id(rs.getInt("ID")).memberId(rs.getInt("MEMBER_ID"))
				.categoryName(rs.getString("CATEGORY_NAME")).categoryType(rs.getString("CATEGORY_TYPE"))
				.createdAt(rs.getDate("CREATED_AT")).updatedAt(rs.getDate("UPDATED_AT")).build();
	}
}
