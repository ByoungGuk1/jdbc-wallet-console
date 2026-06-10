package com.app.wallet.controller;

import com.app.wallet.view.View;

public class TransactionController {

	private final View view;

	public TransactionController(View view) {
		this.view = view;
	}

	public void insertTransaction(int memberId) {
		view.showMessage("거래 등록 기능 준비 중");
	}

	public void selectTransactions(int memberId) {
		view.showMessage("거래 목록 조회 기능 준비 중");
	}

	public void updateTransaction(int memberId) {
		view.showMessage("거래 수정 기능 준비 중");
	}

	public void deleteTransaction(int memberId) {
		view.showMessage("거래 삭제 기능 준비 중");
	}
}