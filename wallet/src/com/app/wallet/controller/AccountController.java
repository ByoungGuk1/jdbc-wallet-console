package com.app.wallet.controller;

import java.util.List;

import com.app.wallet.dto.AccountDTO;
import com.app.wallet.dto.MemberDTO;
import com.app.wallet.service.AccountService;
import com.app.wallet.view.View;

public class AccountController {

	private final View view;
	private final AccountService accountService = new AccountService();

	public AccountController(View view) {
		this.view = view;
	}

	public void runAccountMenu(MemberDTO loginMember) {
		boolean running = true;

		while (running) {
			int menu = view.menu().showAccountMenu();

			try {
				switch (menu) {
				case 1:
					insertAccount(loginMember);
					break;
				case 2:
					selectAccounts(loginMember);
					break;
				case 3:
					selectAccountDetail(loginMember);
					break;
				case 4:
					updateAccount(loginMember);
					break;
				case 5:
					deleteAccount(loginMember);
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

	private void insertAccount(MemberDTO loginMember) {
		AccountDTO account = view.account().inputAccountForInsert(loginMember);
		boolean result = accountService.createAccount(account);

		if (result) {
			view.showMessage("계좌가 등록되었습니다.");
		} else {
			view.showError("계좌 등록에 실패했습니다.");
		}
	}

	private void selectAccounts(MemberDTO loginMember) {
		List<AccountDTO> accounts = accountService.getAccounts(loginMember.getId());
		view.account().printAccounts(accounts);
	}

	private void selectAccountDetail(MemberDTO loginMember) {
		AccountDTO condition = view.account().inputAccountCondition(loginMember);
		AccountDTO account = accountService.getAccount(condition);
		view.account().printAccountDetail(account);
	}

	private void updateAccount(MemberDTO loginMember) {
		AccountDTO account = view.account().inputAccountForUpdate(loginMember);
		boolean result = accountService.updateAccount(account);

		if (result) {
			view.showMessage("계좌가 수정되었습니다.");
		} else {
			view.showError("계좌 수정에 실패했습니다.");
		}
	}

	private void deleteAccount(MemberDTO loginMember) {
		AccountDTO condition = view.account().inputAccountCondition(loginMember);
		boolean result = accountService.deleteAccount(condition);

		if (result) {
			view.showMessage("계좌가 삭제 처리되었습니다.");
		} else {
			view.showError("계좌 삭제에 실패했습니다.");
		}
	}
}
