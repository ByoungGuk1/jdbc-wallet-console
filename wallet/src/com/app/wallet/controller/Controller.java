package com.app.wallet.controller;

import com.app.wallet.dto.MemberDTO;
import com.app.wallet.view.View;

/**
 * 전체 메뉴 흐름을 제어하는 통합 컨트롤러
 */
public class Controller {

	private final View view = new View();

	private final MemberController memberController = new MemberController(view);
	private final AccountController accountController = new AccountController(view);
	private final CategoryController categoryController = new CategoryController(view);
	private final TransactionController transactionController = new TransactionController(view);
	private final TransferController transferController = new TransferController(view);
	private final ReportController reportController = new ReportController(view);

	private MemberDTO loginMember;

	public void run() {
		boolean running = true;

		while (running) {
			if (loginMember == null) {
				running = handleMainMenu();
			} else {
				handleWalletMenu();
			}
		}

		view.close();
	}

	private boolean handleMainMenu() {
		int menu = view.showMainMenu();

		switch (menu) {
		case 1:
			memberController.signup();
			return true;
		case 2:
			loginMember = memberController.login();
			return true;
		case 0:
			view.showMessage("프로그램을 종료합니다.");
			return false;
		default:
			view.showError("잘못된 메뉴입니다.");
			return true;
		}
	}

	private void handleWalletMenu() {
		int menu = view.showWalletMenu(loginMember);
		int memberId = loginMember.getId();

		switch (menu) {
		case 1:
			accountController.insertAccount(memberId);
			break;
		case 2:
			accountController.selectAccounts(memberId);
			break;
		case 3:
			accountController.updateAccount(memberId);
			break;
		case 4:
			accountController.deleteAccount(memberId);
			break;

		case 5:
			categoryController.insertCategory(memberId);
			break;
		case 6:
			categoryController.selectCategories(memberId);
			break;
		case 7:
			categoryController.updateCategory(memberId);
			break;
		case 8:
			categoryController.deleteCategory(memberId);
			break;

		case 9:
			transactionController.insertTransaction(memberId);
			break;
		case 10:
			transactionController.selectTransactions(memberId);
			break;
		case 11:
			transactionController.updateTransaction(memberId);
			break;
		case 12:
			transactionController.deleteTransaction(memberId);
			break;

		case 13:
			transferController.insertTransfer(memberId);
			break;
		case 14:
			transferController.selectTransfers(memberId);
			break;

		case 15:
			reportController.showTotalAssetSummary(memberId);
			break;
		case 16:
			reportController.showAccountBalances(memberId);
			break;
		case 17:
			reportController.showAllHistories(memberId);
			break;
		case 18:
			reportController.showAccountHistories(memberId);
			break;
		case 19:
			reportController.showCategorySummary(memberId);
			break;
		case 20:
			reportController.showAccountCategorySummary(memberId);
			break;
		case 21:
			reportController.showMonthlySummary(memberId);
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