package com.app.wallet.controller;

import com.app.wallet.dto.MemberDTO;
import com.app.wallet.service.MemberService;
import com.app.wallet.view.View;

public class MemberController {

	private final View view;
	private final MemberService memberService = new MemberService();

	public MemberController(View view) {
		this.view = view;
	}

	public void runMemberMenu(MemberDTO loginMember) {
		boolean running = true;

		while (running) {
			int menu = view.menu().showMemberMenu();

			try {
				switch (menu) {
				case 1:
					showMyInfo(loginMember);
					break;
				case 2:
					updateNickname(loginMember);
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

	public void signup() {
		MemberDTO member = view.member().inputSignupInfo();
		boolean result = memberService.signup(member);

		if (result) {
			view.showMessage("회원가입이 완료되었습니다.");
		} else {
			view.showError("이미 사용 중인 이메일입니다.");
		}
	}

	public MemberDTO login() {
		MemberDTO loginInput = view.member().inputLoginInfo();
		MemberDTO loginMember = memberService.login(loginInput);

		if (loginMember == null) {
			view.showError("이메일 또는 비밀번호가 올바르지 않습니다.");
			return null;
		}

		view.showMessage("로그인되었습니다.");
		return loginMember;
	}

	private void showMyInfo(MemberDTO loginMember) {
		MemberDTO member = memberService.getMember(loginMember.getId());
		view.member().printMember(member);
	}

	private void updateNickname(MemberDTO loginMember) {
		MemberDTO updateMember = view.member().inputNicknameForUpdate(loginMember);
		boolean result = memberService.updateNickname(updateMember);

		if (result) {
			loginMember.setMemberNickname(updateMember.getMemberNickname());
			view.showMessage("닉네임이 수정되었습니다.");
		} else {
			view.showError("닉네임 수정에 실패했습니다.");
		}
	}
}
