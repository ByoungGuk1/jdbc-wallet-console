package com.app.wallet.controller;

import com.app.wallet.view.View;

public class ReportController {

	private final View view;

	public ReportController(View view) {
		this.view = view;
	}

	public void showTotalAssetSummary(int memberId) {
		view.showMessage("내 전체 자산 요약 조회 기능 준비 중");
	}

	public void showAccountBalances(int memberId) {
		view.showMessage("계좌별 잔액 조회 기능 준비 중");
	}

	public void showAllHistories(int memberId) {
		view.showMessage("내 자산 전체 내역 조회 기능 준비 중");
	}

	public void showAccountHistories(int memberId) {
		view.showMessage("특정 계좌 전체 내역 조회 기능 준비 중");
	}

	public void showCategorySummary(int memberId) {
		view.showMessage("내 자산 카테고리별 조회 기능 준비 중");
	}

	public void showAccountCategorySummary(int memberId) {
		view.showMessage("특정 계좌 카테고리별 조회 기능 준비 중");
	}

	public void showMonthlySummary(int memberId) {
		view.showMessage("월별 수입/지출 요약 조회 기능 준비 중");
	}
}