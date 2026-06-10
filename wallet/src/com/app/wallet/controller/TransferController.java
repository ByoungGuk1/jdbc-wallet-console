package com.app.wallet.controller;

import com.app.wallet.view.View;

public class TransferController {

	private final View view;

	public TransferController(View view) {
		this.view = view;
	}

	public void insertTransfer(int memberId) {
		view.showMessage("계좌 이체 기능 준비 중");
	}

	public void selectTransfers(int memberId) {
		view.showMessage("이체 내역 조회 기능 준비 중");
	}
}