package com.app.wallet.view;

import com.app.wallet.dto.MemberDTO;

public class MemberView {

	private final View view;

	MemberView(View view) {
		this.view = view;
	}

	public MemberDTO inputSignupInfo() {
		System.out.println();
		System.out.println("===== 회원가입 =====");

		System.out.print("이메일 > ");
		String email = view.inputString();

		System.out.print("비밀번호 > ");
		String password = view.inputString();

		System.out.print("닉네임 > ");
		String nickname = view.inputString();

		return MemberDTO.builder().memberEmail(email).memberPassword(password).memberNickname(nickname).build();
	}

	public MemberDTO inputLoginInfo() {
		System.out.println();
		System.out.println("===== 로그인 =====");

		System.out.print("이메일 > ");
		String email = view.inputString();

		System.out.print("비밀번호 > ");
		String password = view.inputString();

		return MemberDTO.builder().memberEmail(email).memberPassword(password).build();
	}

	public MemberDTO inputNicknameForUpdate(MemberDTO loginMember) {
		System.out.println();
		System.out.println("===== 닉네임 수정 =====");

		System.out.print("새 닉네임 > ");
		String nickname = view.inputString();

		return MemberDTO.builder().id(loginMember.getId()).memberNickname(nickname).build();
	}

	public void printMember(MemberDTO member) {
		System.out.println();
		System.out.println("===== 회원 정보 =====");

		if (member == null) {
			System.out.println("회원 정보가 없습니다.");
			return;
		}

		System.out.println("ID     : " + member.getId());
		System.out.println("이메일 : " + member.getMemberEmail());
		System.out.println("닉네임 : " + member.getMemberNickname());
		System.out.println("권한   : " + member.getMemberRole());
		System.out.println("가입일 : " + member.getCreatedAt());
	}
}
