package com.app.wallet.view;

import java.util.List;

import com.app.wallet.dto.AccountDTO;
import com.app.wallet.dto.MemberDTO;

public class AccountView {

	private final View view;

	AccountView(View view) {
		this.view = view;
	}

	public AccountDTO inputAccountForInsert(MemberDTO loginMember) {
		System.out.println();
		System.out.println("===== 계좌 등록 =====");

		System.out.print("은행명 > ");
		String bankName = view.inputString();

		System.out.print("계좌명 > ");
		String accountName = view.inputString();

		System.out.print("계좌번호 > ");
		String accountNumber = view.inputString();

		System.out.print("초기 잔액 > ");
		long balance = view.inputLong();

		return AccountDTO.builder().memberId(loginMember.getId()).bankName(bankName).accountName(accountName)
				.accountNumber(accountNumber).balance(balance).build();
	}

	public AccountDTO inputAccountForUpdate(MemberDTO loginMember) {
		System.out.println();
		System.out.println("===== 계좌 수정 =====");

		int accountId = view.inputAccountId();

		System.out.print("은행명 > ");
		String bankName = view.inputString();

		System.out.print("계좌명 > ");
		String accountName = view.inputString();

		System.out.print("계좌번호 > ");
		String accountNumber = view.inputString();

		return AccountDTO.builder().id(accountId).memberId(loginMember.getId()).bankName(bankName)
				.accountName(accountName).accountNumber(accountNumber).build();
	}

	public AccountDTO inputAccountCondition(MemberDTO loginMember) {
		int accountId = view.inputAccountId();

		return AccountDTO.builder().id(accountId).memberId(loginMember.getId()).build();
	}

	public void printAccounts(List<AccountDTO> accounts) {
		System.out.println();
		System.out.println("===== 계좌 목록 =====");

		if (accounts == null || accounts.isEmpty()) {
			System.out.println("등록된 계좌가 없습니다.");
			return;
		}

		System.out.printf("%-5s %-10s %-15s %-20s %15s\n", "ID", "은행", "계좌명", "계좌번호", "잔액");

		for (AccountDTO account : accounts) {
			System.out.printf("%-5d %-10s %-15s %-20s %,15d\n", account.getId(), account.getBankName(),
					account.getAccountName(), account.getAccountNumber(), account.getBalance());
		}
	}

	public void printAccountDetail(AccountDTO account) {
		System.out.println();
		System.out.println("===== 계좌 상세 =====");

		if (account == null) {
			System.out.println("계좌 정보가 없습니다.");
			return;
		}

		System.out.println("ID      : " + account.getId());
		System.out.println("은행명  : " + account.getBankName());
		System.out.println("계좌명  : " + account.getAccountName());
		System.out.println("계좌번호: " + account.getAccountNumber());
		System.out.printf("잔액    : %,d원\n", account.getBalance());
		System.out.println("상태    : " + account.getAccountStatus());
	}
}
