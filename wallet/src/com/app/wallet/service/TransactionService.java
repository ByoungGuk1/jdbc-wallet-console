package com.app.wallet.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.app.wallet.dao.AccountDAO;
import com.app.wallet.dao.CategoryDAO;
import com.app.wallet.dao.TransactionDAO;
import com.app.wallet.dto.AccountDTO;
import com.app.wallet.dto.CategoryDTO;
import com.app.wallet.dto.MemberDTO;
import com.app.wallet.dto.TransactionDTO;
import com.app.wallet.util.DBUtil;

public class TransactionService {

	private final TransactionDAO transactionDAO = new TransactionDAO();
	private final AccountDAO accountDAO = new AccountDAO();
	private final CategoryDAO categoryDAO = new CategoryDAO();

	public boolean createTransaction(MemberDTO loginMember, TransactionDTO transaction) {
		Connection conn = null;

		try {
			conn = DBUtil.dbConnect();
			conn.setAutoCommit(false);

			AccountDTO account = getAccountForUpdate(conn, loginMember.getId(), transaction.getAccountId());
			CategoryDTO category = getCategoryForUpdate(conn, loginMember.getId(), transaction.getCategoryId());

			validateType(transaction.getTransactionType(), category.getCategoryType());

			long newBalance = calculateBalance(account.getBalance(), transaction.getTransactionType(),
					transaction.getAmount());
			validateBalance(newBalance);

			int insertResult = transactionDAO.insertTransaction(conn, transaction);

			account.setBalance(newBalance);
			int balanceResult = accountDAO.updateBalance(conn, account);

			DBUtil.commit(conn);
			return insertResult == 1 && balanceResult == 1;
		} catch (SQLException | RuntimeException e) {
			DBUtil.rollback(conn);
			throw new RuntimeException("거래 등록 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, null, null);
		}
	}

	public List<TransactionDTO> getTransactions(int memberId) {
		return transactionDAO.selectTransactionsByMemberId(memberId);
	}

	public List<TransactionDTO> getTransactionsByAccount(AccountDTO condition) {
		return transactionDAO.selectTransactionsByAccountId(condition.getMemberId(), condition.getId());
	}

	public boolean updateTransaction(MemberDTO loginMember, TransactionDTO transaction) {
		Connection conn = null;

		try {
			conn = DBUtil.dbConnect();
			conn.setAutoCommit(false);

			TransactionDTO oldTransaction = transactionDAO.selectTransactionById(conn, transaction.getId(),
					loginMember.getId());

			if (oldTransaction == null) {
				throw new RuntimeException("수정할 거래가 없습니다.");
			}

			AccountDTO account = getAccountForUpdate(conn, loginMember.getId(), oldTransaction.getAccountId());
			CategoryDTO category = getCategoryForUpdate(conn, loginMember.getId(), transaction.getCategoryId());

			validateType(transaction.getTransactionType(), category.getCategoryType());

			long rollbackBalance = rollbackTransaction(account.getBalance(), oldTransaction);
			long newBalance = calculateBalance(rollbackBalance, transaction.getTransactionType(),
					transaction.getAmount());
			validateBalance(newBalance);

			transaction.setAccountId(oldTransaction.getAccountId());

			int updateResult = transactionDAO.updateTransaction(conn, transaction);

			account.setBalance(newBalance);
			int balanceResult = accountDAO.updateBalance(conn, account);

			DBUtil.commit(conn);
			return updateResult == 1 && balanceResult == 1;
		} catch (SQLException | RuntimeException e) {
			DBUtil.rollback(conn);
			throw new RuntimeException("거래 수정 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, null, null);
		}
	}

	public boolean deleteTransaction(MemberDTO loginMember, TransactionDTO condition) {
		Connection conn = null;

		try {
			conn = DBUtil.dbConnect();
			conn.setAutoCommit(false);

			TransactionDTO oldTransaction = transactionDAO.selectTransactionById(conn, condition.getId(),
					loginMember.getId());

			if (oldTransaction == null) {
				throw new RuntimeException("삭제할 거래가 없습니다.");
			}

			AccountDTO account = getAccountForUpdate(conn, loginMember.getId(), oldTransaction.getAccountId());
			long newBalance = rollbackTransaction(account.getBalance(), oldTransaction);
			validateBalance(newBalance);

			int deleteResult = transactionDAO.deleteTransaction(conn, condition.getId());

			account.setBalance(newBalance);
			int balanceResult = accountDAO.updateBalance(conn, account);

			DBUtil.commit(conn);
			return deleteResult == 1 && balanceResult == 1;
		} catch (SQLException | RuntimeException e) {
			DBUtil.rollback(conn);
			throw new RuntimeException("거래 삭제 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, null, null);
		}
	}

	private AccountDTO getAccountForUpdate(Connection conn, int memberId, int accountId) throws SQLException {
		AccountDTO condition = AccountDTO.builder().id(accountId).memberId(memberId).build();

		AccountDTO account = accountDAO.selectAccountById(conn, condition);

		if (account == null) {
			throw new RuntimeException("계좌 정보를 찾을 수 없습니다.");
		}

		return account;
	}

	private CategoryDTO getCategoryForUpdate(Connection conn, int memberId, int categoryId) throws SQLException {
		CategoryDTO condition = CategoryDTO.builder().id(categoryId).memberId(memberId).build();

		CategoryDTO category = categoryDAO.selectCategoryById(conn, condition);

		if (category == null) {
			throw new RuntimeException("카테고리 정보를 찾을 수 없습니다.");
		}

		return category;
	}

	private void validateType(String transactionType, String categoryType) {
		if (!transactionType.equals(categoryType)) {
			throw new RuntimeException("거래 유형과 카테고리 유형이 일치하지 않습니다.");
		}
	}

	private long calculateBalance(long balance, String transactionType, long amount) {
		if ("INCOME".equals(transactionType)) {
			return balance + amount;
		}

		return balance - amount;
	}

	private long rollbackTransaction(long balance, TransactionDTO transaction) {
		if ("INCOME".equals(transaction.getTransactionType())) {
			return balance - transaction.getAmount();
		}

		return balance + transaction.getAmount();
	}

	private void validateBalance(long balance) {
		if (balance < 0) {
			throw new RuntimeException("잔액이 부족합니다.");
		}
	}
}
