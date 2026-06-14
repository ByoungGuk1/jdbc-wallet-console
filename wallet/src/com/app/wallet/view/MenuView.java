package com.app.wallet.view;

import com.app.wallet.dto.MemberDTO;

public class MenuView {

	private final View view;

	MenuView(View view) {
		this.view = view;
	}

	public int showMainMenu() {
		System.out.println();
		System.out.println("===== Project Wallet =====");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("0. 종료");
		System.out.print("선택 > ");
		return view.inputInt();
	}

	public int showHomeMenu(MemberDTO loginMember) {
		System.out.println();
		System.out.println("===== 개인 지갑 관리 =====");
		System.out.println("로그인 사용자 : " + loginMember.getMemberNickname());
		System.out.println();
		System.out.println("1. 회원 관리");
		System.out.println("2. 계좌 관리");
		System.out.println("3. 카테고리 관리");
		System.out.println("4. 거래 관리");
		System.out.println("5. 조회/통계");
		System.out.println("0. 로그아웃");
		System.out.print("선택 > ");
		return view.inputInt();
	}

	public int showMemberMenu() {
		System.out.println();
		System.out.println("===== 회원 관리 =====");
		System.out.println("1. 내 정보 조회");
		System.out.println("2. 닉네임 수정");
		System.out.println("0. 뒤로가기");
		System.out.print("선택 > ");
		return view.inputInt();
	}

	public int showAccountMenu() {
		System.out.println();
		System.out.println("===== 계좌 관리 =====");
		System.out.println("1. 계좌 등록");
		System.out.println("2. 계좌 목록 조회");
		System.out.println("3. 계좌 상세 조회");
		System.out.println("4. 계좌 수정");
		System.out.println("5. 계좌 삭제");
		System.out.println("0. 뒤로가기");
		System.out.print("선택 > ");
		return view.inputInt();
	}

	public int showCategoryMenu() {
		System.out.println();
		System.out.println("===== 카테고리 관리 =====");
		System.out.println("1. 카테고리 등록");
		System.out.println("2. 카테고리 목록 조회");
		System.out.println("3. 수입/지출별 카테고리 조회");
		System.out.println("4. 카테고리 수정");
		System.out.println("5. 카테고리 삭제");
		System.out.println("0. 뒤로가기");
		System.out.print("선택 > ");
		return view.inputInt();
	}

	public int showTransactionMenu() {
		System.out.println();
		System.out.println("===== 거래 관리 =====");
		System.out.println("1. 거래 등록");
		System.out.println("2. 전체 거래 목록 조회");
		System.out.println("3. 특정 계좌 거래 목록 조회");
		System.out.println("4. 거래 수정");
		System.out.println("5. 거래 삭제");
		System.out.println("0. 뒤로가기");
		System.out.print("선택 > ");
		return view.inputInt();
	}

	public int showReportMenu() {
		System.out.println();
		System.out.println("===== 조회/통계 =====");
		System.out.println("1. 총 자산 조회");
		System.out.println("2. 계좌별 잔액 조회");
		System.out.println("3. 월별 수입/지출 조회");
		System.out.println("4. 카테고리별 조회");
		System.out.println("0. 뒤로가기");
		System.out.print("선택 > ");
		return view.inputInt();
	}
}
