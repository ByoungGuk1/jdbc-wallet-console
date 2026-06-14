package com.app.wallet.view;

import java.util.List;

import com.app.wallet.dto.CategorySummaryDTO;
import com.app.wallet.dto.MonthlySummaryDTO;

public class ReportView {

	private final View view;

	ReportView(View view) {
		this.view = view;
	}

	public String inputMonth() {
		System.out.print("조회 월(YYYY-MM) > ");
		return view.inputString();
	}

	public void printTotalBalance(long totalBalance) {
		System.out.println();
		System.out.println("===== 총 자산 =====");
		System.out.printf("총 잔액 : %,d원\n", totalBalance);
	}

	public void printMonthlySummaries(List<MonthlySummaryDTO> summaries) {
		System.out.println();
		System.out.println("===== 월별 수입/지출 =====");

		if (summaries == null || summaries.isEmpty()) {
			System.out.println("조회 결과가 없습니다.");
			return;
		}

		System.out.printf("%-10s %15s %15s %15s\n", "월", "수입", "지출", "순수익");

		for (MonthlySummaryDTO summary : summaries) {
			System.out.printf("%-10s %,15d %,15d %,15d\n", summary.getMonthValue(), summary.getIncomeAmount(),
					summary.getExpenseAmount(), summary.getNetAmount());
		}
	}

	public void printCategorySummaries(List<CategorySummaryDTO> summaries) {
		System.out.println();
		System.out.println("===== 카테고리별 조회 =====");

		if (summaries == null || summaries.isEmpty()) {
			System.out.println("조회 결과가 없습니다.");
			return;
		}

		System.out.printf("%-15s %-10s %15s\n", "카테고리", "유형", "합계");

		for (CategorySummaryDTO summary : summaries) {
			System.out.printf("%-15s %-10s %,15d\n", summary.getCategoryName(), summary.getCategoryType(),
					summary.getTotalAmount());
		}
	}
}
