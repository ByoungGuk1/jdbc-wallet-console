package com.app.wallet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.wallet.dto.CategorySummaryDTO;
import com.app.wallet.dto.MonthlySummaryDTO;
import com.app.wallet.dto.TransactionDTO;
import com.app.wallet.query.TransactionQuery;
import com.app.wallet.util.DBUtil;

public class TransactionDAO {

	public int insertTransaction(Connection conn, TransactionDTO transaction) throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(TransactionQuery.INSERT_TRANSACTION);

			pst.setInt(1, transaction.getAccountId());
			pst.setInt(2, transaction.getCategoryId());
			pst.setString(3, transaction.getTransactionType());
			pst.setLong(4, transaction.getAmount());
			pst.setDate(5, transaction.getTransactionDate());
			pst.setString(6, transaction.getMemo());

			return pst.executeUpdate();
		} finally {
			DBUtil.dbDisconnect(null, pst, null);
		}
	}

	public List<TransactionDTO> selectTransactionsByMemberId(int memberId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<TransactionDTO> transactions = new ArrayList<>();

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(TransactionQuery.SELECT_TRANSACTIONS_BY_MEMBER_ID);
			pst.setInt(1, memberId);
			rs = pst.executeQuery();

			while (rs.next()) {
				transactions.add(makeTransaction(rs));
			}

			return transactions;
		} catch (SQLException e) {
			throw new RuntimeException("거래 목록 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public List<TransactionDTO> selectTransactionsByAccountId(int memberId, int accountId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<TransactionDTO> transactions = new ArrayList<>();

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(TransactionQuery.SELECT_TRANSACTIONS_BY_ACCOUNT_ID);
			pst.setInt(1, memberId);
			pst.setInt(2, accountId);
			rs = pst.executeQuery();

			while (rs.next()) {
				transactions.add(makeTransaction(rs));
			}

			return transactions;
		} catch (SQLException e) {
			throw new RuntimeException("계좌별 거래 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public TransactionDTO selectTransactionById(Connection conn, int transactionId, int memberId) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement(TransactionQuery.SELECT_TRANSACTION_BY_ID);
			pst.setInt(1, transactionId);
			pst.setInt(2, memberId);
			rs = pst.executeQuery();

			if (rs.next()) {
				return makeTransaction(rs);
			}

			return null;
		} finally {
			DBUtil.dbDisconnect(null, pst, rs);
		}
	}

	public int updateTransaction(Connection conn, TransactionDTO transaction) throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(TransactionQuery.UPDATE_TRANSACTION);

			pst.setInt(1, transaction.getCategoryId());
			pst.setString(2, transaction.getTransactionType());
			pst.setLong(3, transaction.getAmount());
			pst.setDate(4, transaction.getTransactionDate());
			pst.setString(5, transaction.getMemo());
			pst.setInt(6, transaction.getId());

			return pst.executeUpdate();
		} finally {
			DBUtil.dbDisconnect(null, pst, null);
		}
	}

	public int deleteTransaction(Connection conn, int transactionId) throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(TransactionQuery.DELETE_TRANSACTION);
			pst.setInt(1, transactionId);

			return pst.executeUpdate();
		} finally {
			DBUtil.dbDisconnect(null, pst, null);
		}
	}

	public List<MonthlySummaryDTO> selectMonthlySummary(int memberId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MonthlySummaryDTO> summaries = new ArrayList<>();

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(TransactionQuery.SELECT_MONTHLY_SUMMARY);
			pst.setInt(1, memberId);
			rs = pst.executeQuery();

			while (rs.next()) {
				summaries.add(MonthlySummaryDTO.builder().monthValue(rs.getString("MONTH_VALUE"))
						.incomeAmount(rs.getLong("INCOME_AMOUNT")).expenseAmount(rs.getLong("EXPENSE_AMOUNT"))
						.netAmount(rs.getLong("NET_AMOUNT")).build());
			}

			return summaries;
		} catch (SQLException e) {
			throw new RuntimeException("월별 요약 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public List<CategorySummaryDTO> selectCategorySummary(int memberId, String monthValue) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<CategorySummaryDTO> summaries = new ArrayList<>();

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(TransactionQuery.SELECT_CATEGORY_SUMMARY);
			pst.setInt(1, memberId);
			pst.setString(2, monthValue);
			rs = pst.executeQuery();

			while (rs.next()) {
				summaries.add(CategorySummaryDTO.builder().categoryName(rs.getString("CATEGORY_NAME"))
						.categoryType(rs.getString("CATEGORY_TYPE")).totalAmount(rs.getLong("TOTAL_AMOUNT")).build());
			}

			return summaries;
		} catch (SQLException e) {
			throw new RuntimeException("카테고리별 요약 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	private TransactionDTO makeTransaction(ResultSet rs) throws SQLException {
		Date transactionDate = rs.getDate("TRANSACTION_DATE");

		return TransactionDTO.builder().id(rs.getInt("ID")).accountId(rs.getInt("ACCOUNT_ID"))
				.accountName(rs.getString("ACCOUNT_NAME")).categoryId(rs.getInt("CATEGORY_ID"))
				.categoryName(rs.getString("CATEGORY_NAME")).transactionType(rs.getString("TRANSACTION_TYPE"))
				.amount(rs.getLong("AMOUNT")).transactionDate(transactionDate).memo(rs.getString("MEMO"))
				.createdAt(rs.getDate("CREATED_AT")).updatedAt(rs.getDate("UPDATED_AT")).build();
	}
}
