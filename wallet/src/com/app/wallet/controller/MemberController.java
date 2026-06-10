package com.app.wallet.controller;

import com.app.wallet.dto.MemberDTO;
import com.app.wallet.view.View;

public class MemberController {

	private final View view;

	public MemberController(View view) {
		this.view = view;
	}

	public void signup() {
		view.showMessage("회원가입 기능 준비 중");
	}

	public MemberDTO login() {
		view.showMessage("로그인 기능 준비 중");

		return new MemberDTO(1, "test@test.com", "1234", "테스트사용자", "USER", null, null);
	}
}