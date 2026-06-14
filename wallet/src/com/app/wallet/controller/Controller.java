package com.app.wallet.controller;

import com.app.wallet.dto.MemberDTO;
import com.app.wallet.view.View;

public class Controller {

	private final View view = new View();

	private final MemberController memberController = new MemberController(view);
	private final AccountController accountController = new AccountController(view);
	private final CategoryController categoryController = new CategoryController(view);
	private final TransactionController transactionController = new TransactionController(view);
	private final ReportController reportController = new ReportController(view);

	private MemberDTO loginMember;

	public void run() {
		boolean running = true;

		while (running) {
			if (loginMember == null) {
				running = handleMainMenu();
			} else {
				handleHomeMenu();
			}
		}

		view.close();
	}

	private boolean handleMainMenu() {
		int menu = view.menu().showMainMenu();
		boolean result = true;

		try {
			switch (menu) {
			case 1:
				memberController.signup();
				break;
			case 2:
				loginMember = memberController.login();
				break;
			case 0:
				view.showMessage("프로그램을 종료합니다.");
				result = false;
				break;
			default:
				view.showError("잘못된 메뉴입니다.");
			}
		} catch (RuntimeException e) {
			view.showError(e.getMessage());
		}
		return result;
	}

	private void handleHomeMenu() {
		int menu = view.menu().showHomeMenu(loginMember);

		switch (menu) {
		case 1:
			memberController.runMemberMenu(loginMember);
			break;
		case 2:
			accountController.runAccountMenu(loginMember);
			break;
		case 3:
			categoryController.runCategoryMenu(loginMember);
			break;
		case 4:
			transactionController.runTransactionMenu(loginMember);
			break;
		case 5:
			reportController.runReportMenu(loginMember);
			break;
		case 0:
			loginMember = null;
			view.showMessage("로그아웃되었습니다.");
			break;
		default:
			view.showError("잘못된 메뉴입니다.");
		}
	}
}
