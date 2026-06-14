package com.app.wallet.controller;

import java.util.List;

import com.app.wallet.dto.AccountDTO;
import com.app.wallet.dto.MemberDTO;
import com.app.wallet.dto.TransactionDTO;
import com.app.wallet.service.TransactionService;
import com.app.wallet.view.View;

public class TransactionController {

	private final View view;
	private final TransactionService transactionService = new TransactionService();

	public TransactionController(View view) {
		this.view = view;
	}

	public void runTransactionMenu(MemberDTO loginMember) {
		boolean running = true;

		while (running) {
			int menu = view.menu().showTransactionMenu();

			try {
				switch (menu) {
				case 1:
					insertTransaction(loginMember);
					break;
				case 2:
					selectTransactions(loginMember);
					break;
				case 3:
					selectTransactionsByAccount(loginMember);
					break;
				case 4:
					updateTransaction(loginMember);
					break;
				case 5:
					deleteTransaction(loginMember);
					break;
				case 0:
					running = false;
					break;
				default:
					view.showError("잘못된 메뉴입니다.");
				}
			} catch (RuntimeException e) {
				view.showError(e.getMessage());
			}
		}
	}

	private void insertTransaction(MemberDTO loginMember) {
		TransactionDTO transaction = view.transaction().inputTransactionForInsert();
		boolean result = transactionService.createTransaction(loginMember, transaction);

		if (result) {
			view.showMessage("거래가 등록되었습니다.");
		} else {
			view.showError("거래 등록에 실패했습니다.");
		}
	}

	private void selectTransactions(MemberDTO loginMember) {
		List<TransactionDTO> transactions = transactionService.getTransactions(loginMember.getId());
		view.transaction().printTransactions(transactions);
	}

	private void selectTransactionsByAccount(MemberDTO loginMember) {
		AccountDTO condition = view.account().inputAccountCondition(loginMember);
		List<TransactionDTO> transactions = transactionService.getTransactionsByAccount(condition);
		view.transaction().printTransactions(transactions);
	}

	private void updateTransaction(MemberDTO loginMember) {
		TransactionDTO transaction = view.transaction().inputTransactionForUpdate();
		boolean result = transactionService.updateTransaction(loginMember, transaction);

		if (result) {
			view.showMessage("거래가 수정되었습니다.");
		} else {
			view.showError("거래 수정에 실패했습니다.");
		}
	}

	private void deleteTransaction(MemberDTO loginMember) {
		TransactionDTO condition = view.transaction().inputTransactionCondition();
		boolean result = transactionService.deleteTransaction(loginMember, condition);

		if (result) {
			view.showMessage("거래가 삭제되었습니다.");
		} else {
			view.showError("거래 삭제에 실패했습니다.");
		}
	}
}
