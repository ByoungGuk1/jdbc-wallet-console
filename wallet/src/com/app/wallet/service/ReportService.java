package com.app.wallet.service;

import java.util.List;

import com.app.wallet.dao.AccountDAO;
import com.app.wallet.dao.TransactionDAO;
import com.app.wallet.dto.AccountDTO;
import com.app.wallet.dto.CategorySummaryDTO;
import com.app.wallet.dto.MonthlySummaryDTO;

public class ReportService {

	private final AccountDAO accountDAO = new AccountDAO();
	private final TransactionDAO transactionDAO = new TransactionDAO();

	public long getTotalBalance(int memberId) {
		return accountDAO.selectTotalBalance(memberId);
	}

	public List<AccountDTO> getAccountBalances(int memberId) {
		return accountDAO.selectAccountsByMemberId(memberId);
	}

	public List<MonthlySummaryDTO> getMonthlySummary(int memberId) {
		return transactionDAO.selectMonthlySummary(memberId);
	}

	public List<CategorySummaryDTO> getCategorySummary(int memberId, String monthValue) {
		return transactionDAO.selectCategorySummary(memberId, monthValue);
	}
}
