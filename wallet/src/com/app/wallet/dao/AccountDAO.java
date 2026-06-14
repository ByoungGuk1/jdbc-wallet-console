package com.app.wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.wallet.dto.AccountDTO;
import com.app.wallet.query.AccountQuery;
import com.app.wallet.util.DBUtil;

public class AccountDAO {

	public int insertAccount(AccountDTO account) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(AccountQuery.INSERT_ACCOUNT);

			pst.setInt(1, account.getMemberId());
			pst.setString(2, account.getBankName());
			pst.setString(3, account.getAccountName());
			pst.setString(4, account.getAccountNumber());
			pst.setLong(5, account.getBalance());

			return pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("계좌 등록 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}
	}

	public List<AccountDTO> selectAccountsByMemberId(int memberId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<AccountDTO> accounts = new ArrayList<>();

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(AccountQuery.SELECT_ACCOUNTS_BY_MEMBER_ID);
			pst.setInt(1, memberId);
			rs = pst.executeQuery();

			while (rs.next()) {
				accounts.add(makeAccount(rs));
			}

			return accounts;
		} catch (SQLException e) {
			throw new RuntimeException("계좌 목록 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public AccountDTO selectAccountById(AccountDTO condition) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(AccountQuery.SELECT_ACCOUNT_BY_ID);
			pst.setInt(1, condition.getId());
			pst.setInt(2, condition.getMemberId());
			rs = pst.executeQuery();

			if (rs.next()) {
				return makeAccount(rs);
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException("계좌 상세 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public AccountDTO selectAccountById(Connection conn, AccountDTO condition) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement(AccountQuery.SELECT_ACCOUNT_BY_ID);
			pst.setInt(1, condition.getId());
			pst.setInt(2, condition.getMemberId());
			rs = pst.executeQuery();

			if (rs.next()) {
				return makeAccount(rs);
			}

			return null;
		} finally {
			DBUtil.dbDisconnect(null, pst, rs);
		}
	}

	public int updateAccount(AccountDTO account) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(AccountQuery.UPDATE_ACCOUNT);

			pst.setString(1, account.getBankName());
			pst.setString(2, account.getAccountName());
			pst.setString(3, account.getAccountNumber());
			pst.setInt(4, account.getId());
			pst.setInt(5, account.getMemberId());

			return pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("계좌 수정 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}
	}

	public int deleteAccountLogically(AccountDTO condition) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(AccountQuery.DELETE_ACCOUNT_LOGICALLY);

			pst.setInt(1, condition.getId());
			pst.setInt(2, condition.getMemberId());

			return pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("계좌 삭제 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}
	}

	public int updateBalance(Connection conn, AccountDTO account) throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(AccountQuery.UPDATE_BALANCE);

			pst.setLong(1, account.getBalance());
			pst.setInt(2, account.getId());
			pst.setInt(3, account.getMemberId());

			return pst.executeUpdate();
		} finally {
			DBUtil.dbDisconnect(null, pst, null);
		}
	}

	public long selectTotalBalance(int memberId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(AccountQuery.SELECT_TOTAL_BALANCE);
			pst.setInt(1, memberId);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong("TOTAL_BALANCE");
			}

			return 0;
		} catch (SQLException e) {
			throw new RuntimeException("총 자산 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	private AccountDTO makeAccount(ResultSet rs) throws SQLException {
		return AccountDTO.builder().id(rs.getInt("ID")).memberId(rs.getInt("MEMBER_ID"))
				.bankName(rs.getString("BANK_NAME")).accountName(rs.getString("ACCOUNT_NAME"))
				.accountNumber(rs.getString("ACCOUNT_NUMBER")).balance(rs.getLong("BALANCE"))
				.accountStatus(rs.getString("ACCOUNT_STATUS")).createdAt(rs.getDate("CREATED_AT"))
				.updatedAt(rs.getDate("UPDATED_AT")).build();
	}
}
