package com.app.wallet.controller;

import com.app.wallet.view.View;

public class AccountController {

	private final View view;

	public AccountController(View view) {
		this.view = view;
	}

	public void insertAccount(int memberId) {
		view.showMessage("계좌 등록 기능 준비 중");
	}

	public void selectAccounts(int memberId) {
		view.showMessage("계좌 목록 조회 기능 준비 중");
	}

	public void updateAccount(int memberId) {
		view.showMessage("계좌 수정 기능 준비 중");
	}

	public void deleteAccount(int memberId) {
		view.showMessage("계좌 삭제 기능 준비 중");
	}
}