package com.app.wallet.view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class View {

	private final Scanner sc = new Scanner(System.in);

	private final MenuView menuView = new MenuView(this);
	private final MemberView memberView = new MemberView(this);
	private final AccountView accountView = new AccountView(this);
	private final CategoryView categoryView = new CategoryView(this);
	private final TransactionView transactionView = new TransactionView(this);
	private final ReportView reportView = new ReportView(this);

	public MenuView menu() {
		return menuView;
	}

	public MemberView member() {
		return memberView;
	}

	public AccountView account() {
		return accountView;
	}

	public CategoryView category() {
		return categoryView;
	}

	public TransactionView transaction() {
		return transactionView;
	}

	public ReportView report() {
		return reportView;
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	public void showError(String message) {
		System.err.println("[오류] " + message);
	}

	public void close() {
		sc.close();
	}

	String inputString() {
		return sc.nextLine().trim();
	}

	int inputInt() {
		while (true) {
			try {
				return Integer.parseInt(inputString());
			} catch (NumberFormatException e) {
				System.out.print("숫자로 다시 입력 > ");
			}
		}
	}

	long inputLong() {
		while (true) {
			try {
				return Long.parseLong(inputString());
			} catch (NumberFormatException e) {
				System.out.print("숫자로 다시 입력 > ");
			}
		}
	}

	Date inputDate() {
		while (true) {
			try {
				return Date.valueOf(inputString());
			} catch (IllegalArgumentException e) {
				System.out.print("날짜 형식 오류. 다시 입력(YYYY-MM-DD) > ");
			}
		}
	}

	Date inputOptionalDate() {
		while (true) {
			try {
				String input = inputString();

				if (input.isEmpty()) {
					return Date.valueOf(LocalDate.now());
				}

				return Date.valueOf(input);
			} catch (IllegalArgumentException e) {
				System.out.print("날짜 형식 오류. 다시 입력(YYYY-MM-DD, 엔터 시 오늘) > ");
			}
		}
	}

	int inputAccountId() {
		System.out.print("계좌 ID > ");
		return inputInt();
	}

	int inputCategoryId() {
		System.out.print("카테고리 ID > ");
		return inputInt();
	}

	int inputTransactionId() {
		System.out.print("거래 ID > ");
		return inputInt();
	}
}
