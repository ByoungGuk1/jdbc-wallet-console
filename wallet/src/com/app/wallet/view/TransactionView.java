package com.app.wallet.view;

import java.util.List;

import com.app.wallet.dto.TransactionDTO;

public class TransactionView {

	private final View view;

	TransactionView(View view) {
		this.view = view;
	}

	public TransactionDTO inputTransactionForInsert() {
		System.out.println();
		System.out.println("===== 거래 등록 =====");

		int accountId = view.inputAccountId();
		int categoryId = view.inputCategoryId();
		String type = inputTransactionType();

		System.out.print("금액 > ");
		long amount = view.inputLong();

		System.out.print("거래 날짜(YYYY-MM-DD, 엔터 시 오늘) > ");
		java.sql.Date transactionDate = view.inputOptionalDate();

		System.out.print("메모 > ");
		String memo = view.inputString();

		return TransactionDTO.builder().accountId(accountId).categoryId(categoryId).transactionType(type).amount(amount)
				.transactionDate(transactionDate).memo(memo).build();
	}

	public TransactionDTO inputTransactionForUpdate() {
		System.out.println();
		System.out.println("===== 거래 수정 =====");

		int transactionId = view.inputTransactionId();
		int categoryId = view.inputCategoryId();
		String type = inputTransactionType();

		System.out.print("금액 > ");
		long amount = view.inputLong();

		System.out.print("거래 날짜(YYYY-MM-DD, 엔터 시 오늘) > ");
		java.sql.Date transactionDate = view.inputOptionalDate();

		System.out.print("메모 > ");
		String memo = view.inputString();

		return TransactionDTO.builder().id(transactionId).categoryId(categoryId).transactionType(type).amount(amount)
				.transactionDate(transactionDate).memo(memo).build();
	}

	public TransactionDTO inputTransactionCondition() {
		int transactionId = view.inputTransactionId();

		return TransactionDTO.builder().id(transactionId).build();
	}

	private String inputTransactionType() {
		while (true) {
			System.out.print("거래 유형(INCOME/EXPENSE) > ");
			String type = view.inputString().toUpperCase();

			if ("INCOME".equals(type) || "EXPENSE".equals(type)) {
				return type;
			}

			view.showError("INCOME 또는 EXPENSE만 입력 가능합니다.");
		}
	}

	public void printTransactions(List<TransactionDTO> transactions) {
		System.out.println();
		System.out.println("===== 거래 목록 =====");

		if (transactions == null || transactions.isEmpty()) {
			System.out.println("거래 내역이 없습니다.");
			return;
		}

		System.out.printf("%-5s %-15s %-15s %-10s %15s %-12s %-20s\n", "ID", "계좌명", "카테고리", "유형", "금액", "날짜", "메모");

		for (TransactionDTO transaction : transactions) {
			System.out.printf("%-5d %-15s %-15s %-10s %,15d %-12s %-20s\n", transaction.getId(),
					transaction.getAccountName(), transaction.getCategoryName(), transaction.getTransactionType(),
					transaction.getAmount(), transaction.getTransactionDate(), transaction.getMemo());
		}
	}
}
