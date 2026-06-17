package com.app.wallet.service;

import java.util.List;

import com.app.wallet.dao.AccountDAO;
import com.app.wallet.dto.AccountDTO;

public class AccountService {

	private final AccountDAO accountDAO = new AccountDAO();

	public boolean createAccount(AccountDTO account) {
		return accountDAO.insertAccount(account) == 1;
	}

	public List<AccountDTO> getAccounts(int memberId) {
		return accountDAO.selectAccountsByMemberId(memberId);
	}

	public AccountDTO getAccount(AccountDTO condition) {
		return accountDAO.selectAccountById(condition);
	}

	public boolean updateAccount(AccountDTO account) {
		AccountDTO original = accountDAO.selectAccountById(account);
		if (account.getAccountName().isEmpty()) {
			account.setAccountName(original.getAccountName());
		}
		if (account.getBankName().isEmpty()) {
			account.setBankName(original.getBankName());
		}
		if (account.getAccountNumber().isEmpty()) {
			account.setAccountNumber(original.getAccountNumber());
		}
		return accountDAO.updateAccount(account) == 1;
	}

	public boolean deleteAccount(AccountDTO condition) {
		return accountDAO.deleteAccountLogically(condition) == 1;
	}
}
