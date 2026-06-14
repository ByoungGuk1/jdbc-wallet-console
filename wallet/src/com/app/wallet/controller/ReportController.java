package com.app.wallet.controller;

import java.util.List;

import com.app.wallet.dto.AccountDTO;
import com.app.wallet.dto.CategorySummaryDTO;
import com.app.wallet.dto.MemberDTO;
import com.app.wallet.dto.MonthlySummaryDTO;
import com.app.wallet.service.ReportService;
import com.app.wallet.view.View;

public class ReportController {

	private final View view;
	private final ReportService reportService = new ReportService();

	public ReportController(View view) {
		this.view = view;
	}

	public void runReportMenu(MemberDTO loginMember) {
		boolean running = true;

		while (running) {
			int menu = view.menu().showReportMenu();

			try {
				switch (menu) {
				case 1:
					showTotalBalance(loginMember);
					break;
				case 2:
					showAccountBalances(loginMember);
					break;
				case 3:
					showMonthlySummary(loginMember);
					break;
				case 4:
					showCategorySummary(loginMember);
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

	private void showTotalBalance(MemberDTO loginMember) {
		long totalBalance = reportService.getTotalBalance(loginMember.getId());
		view.report().printTotalBalance(totalBalance);
	}

	private void showAccountBalances(MemberDTO loginMember) {
		List<AccountDTO> accounts = reportService.getAccountBalances(loginMember.getId());
		view.account().printAccounts(accounts);
	}

	private void showMonthlySummary(MemberDTO loginMember) {
		List<MonthlySummaryDTO> summaries = reportService.getMonthlySummary(loginMember.getId());
		view.report().printMonthlySummaries(summaries);
	}

	private void showCategorySummary(MemberDTO loginMember) {
		String monthValue = view.report().inputMonth();
		List<CategorySummaryDTO> summaries = reportService.getCategorySummary(loginMember.getId(), monthValue);
		view.report().printCategorySummaries(summaries);
	}
}
