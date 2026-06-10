package com.app.wallet.view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.app.wallet.dto.AccountDTO;
import com.app.wallet.dto.AccountHistoryDTO;
import com.app.wallet.dto.CategoryDTO;
import com.app.wallet.dto.CategorySummaryDTO;
import com.app.wallet.dto.MemberDTO;
import com.app.wallet.dto.MonthlySummaryDTO;
import com.app.wallet.dto.TransactionDTO;
import com.app.wallet.dto.TransferDTO;

public class View {
	private final Scanner sc = new Scanner(System.in);

	public int showMainMenu() {
		System.out.println();
		System.out.println("===== Project Wallet =====");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("0. 종료");
		System.out.print("선택 > ");
		return inputInt();
	}

	public int showWalletMenu(MemberDTO loginMember) {
		System.out.println();
		System.out.println("===== 개인 지갑 관리 =====");
		System.out.println("로그인 사용자 : " + loginMember.getMemberNickname());
		System.out.println();
		System.out.println("1. 계좌 등록");
		System.out.println("2. 계좌 목록 조회");
		System.out.println("3. 계좌 수정");
		System.out.println("4. 계좌 삭제");
		System.out.println();
		System.out.println("5. 카테고리 등록");
		System.out.println("6. 카테고리 목록 조회");
		System.out.println("7. 카테고리 수정");
		System.out.println("8. 카테고리 삭제");
		System.out.println();
		System.out.println("9. 거래 등록");
		System.out.println("10. 거래 목록 조회");
		System.out.println("11. 거래 수정");
		System.out.println("12. 거래 삭제");
		System.out.println();
		System.out.println("13. 계좌 이체");
		System.out.println("14. 이체 내역 조회");
		System.out.println();
		System.out.println("15. 내 전체 자산 요약 조회");
		System.out.println("16. 계좌별 잔액 조회");
		System.out.println("17. 내 자산 전체 내역 조회");
		System.out.println("18. 특정 계좌 전체 내역 조회");
		System.out.println("19. 내 자산 카테고리별 조회");
		System.out.println("20. 특정 계좌 카테고리별 조회");
		System.out.println("21. 월별 수입/지출 요약 조회");
		System.out.println();
		System.out.println("0. 로그아웃");
		System.out.print("선택 > ");
		return inputInt();
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

	/*
	 * ========================================================= 공통 입력
	 * =========================================================
	 */

	public String inputEmail() {
		System.out.print("이메일 > ");
		return inputString();
	}

	public String inputPassword() {
		System.out.print("비밀번호 > ");
		return inputString();
	}

	public String inputNickname() {
		System.out.print("닉네임 > ");
		return inputString();
	}

	public int inputAccountId() {
		System.out.print("계좌 ID > ");
		return inputInt();
	}

	public int inputCategoryId() {
		System.out.print("카테고리 ID > ");
		return inputInt();
	}

	public int inputTransactionId() {
		System.out.print("거래 ID > ");
		return inputInt();
	}

	public int inputTransferId() {
		System.out.print("이체 ID > ");
		return inputInt();
	}

	public String inputMonth() {
		System.out.print("조회 월(YYYY-MM) > ");
		return inputString();
	}

	public String inputKeyword() {
		System.out.print("검색어 > ");
		return inputString();
	}

	public Date inputStartDate() {
		System.out.print("시작 날짜(YYYY-MM-DD) > ");
		return inputDate();
	}

	public Date inputEndDate() {
		System.out.print("종료 날짜(YYYY-MM-DD) > ");
		return inputDate();
	}

	/*
	 * ========================================================= 회원 입력
	 * =========================================================
	 */

	public String[] inputSignupInfo() {
		System.out.println();
		System.out.println("===== 회원가입 =====");

		String email = inputEmail();
		String password = inputPassword();
		String nickname = inputNickname();

		return new String[] { email, password, nickname };
	}

	public String[] inputLoginInfo() {
		System.out.println();
		System.out.println("===== 로그인 =====");

		String email = inputEmail();
		String password = inputPassword();

		return new String[] { email, password };
	}

	/*
	 * ========================================================= 계좌 입력
	 * =========================================================
	 */

	public AccountDTO inputAccountForInsert(int memberId) {
		System.out.println();
		System.out.println("===== 계좌 등록 =====");

		System.out.print("은행명 > ");
		String bankName = inputString();

		System.out.print("계좌명 > ");
		String accountName = inputString();

		System.out.print("계좌번호 > ");
		String accountNumber = inputString();

		System.out.print("초기 잔액 > ");
		long balance = inputLong();

		return new AccountDTO(0, memberId, bankName, accountName, accountNumber, balance, "ACTIVE", null, null);
	}

	public AccountDTO inputAccountForUpdate(int accountId, int memberId) {
		System.out.println();
		System.out.println("===== 계좌 수정 =====");

		System.out.print("은행명 > ");
		String bankName = inputString();

		System.out.print("계좌명 > ");
		String accountName = inputString();

		System.out.print("계좌번호 > ");
		String accountNumber = inputString();

		return new AccountDTO(accountId, memberId, bankName, accountName, accountNumber, 0, null, null, null);
	}

	/*
	 * ========================================================= 카테고리 입력
	 * =========================================================
	 */

	public CategoryDTO inputCategoryForInsert(int memberId) {
		System.out.println();
		System.out.println("===== 카테고리 등록 =====");

		System.out.print("카테고리명 > ");
		String categoryName = inputString();

		String categoryType = inputCategoryType();

		return new CategoryDTO(0, memberId, categoryName, categoryType, null, null);
	}

	public CategoryDTO inputCategoryForUpdate(int categoryId, int memberId) {
		System.out.println();
		System.out.println("===== 카테고리 수정 =====");

		System.out.print("카테고리명 > ");
		String categoryName = inputString();

		String categoryType = inputCategoryType();

		return new CategoryDTO(categoryId, memberId, categoryName, categoryType, null, null);
	}

	private String inputCategoryType() {
		while (true) {
			System.out.print("카테고리 유형(INCOME/EXPENSE) > ");
			String type = inputString().toUpperCase();

			if ("INCOME".equals(type) || "EXPENSE".equals(type)) {
				return type;
			}

			showError("카테고리 유형은 INCOME 또는 EXPENSE만 입력 가능합니다.");
		}
	}

	/*
	 * ========================================================= 거래 입력
	 * =========================================================
	 */

	public TransactionDTO inputTransactionForInsert() {
		System.out.println();
		System.out.println("===== 거래 등록 =====");

		int accountId = inputAccountId();
		int categoryId = inputCategoryId();
		String transactionType = inputTransactionType();

		System.out.print("금액 > ");
		long amount = inputLong();

		System.out.print("거래 날짜(YYYY-MM-DD, 엔터 시 오늘) > ");
		Date transactionDate = inputOptionalDate();

		System.out.print("메모 > ");
		String memo = inputString();

		return new TransactionDTO(0, accountId, null, categoryId, null, transactionType, amount, transactionDate, memo,
				null, null);
	}

	public TransactionDTO inputTransactionForUpdate(int transactionId) {
		System.out.println();
		System.out.println("===== 거래 수정 =====");

		int categoryId = inputCategoryId();
		String transactionType = inputTransactionType();

		System.out.print("금액 > ");
		long amount = inputLong();

		System.out.print("거래 날짜(YYYY-MM-DD, 엔터 시 오늘) > ");
		Date transactionDate = inputOptionalDate();

		System.out.print("메모 > ");
		String memo = inputString();

		return new TransactionDTO(transactionId, 0, null, categoryId, null, transactionType, amount, transactionDate,
				memo, null, null);
	}

	private String inputTransactionType() {
		while (true) {
			System.out.print("거래 유형(INCOME/EXPENSE) > ");
			String type = inputString().toUpperCase();

			if ("INCOME".equals(type) || "EXPENSE".equals(type)) {
				return type;
			}

			showError("거래 유형은 INCOME 또는 EXPENSE만 입력 가능합니다.");
		}
	}

	/*
	 * ========================================================= 이체 입력
	 * =========================================================
	 */

	public TransferDTO inputTransferForInsert() {
		System.out.println();
		System.out.println("===== 계좌 이체 =====");

		System.out.print("출금 계좌 ID > ");
		int fromAccountId = inputInt();

		System.out.print("입금 계좌 ID > ");
		int toAccountId = inputInt();

		System.out.print("이체 금액 > ");
		long amount = inputLong();

		System.out.print("이체 날짜(YYYY-MM-DD, 엔터 시 오늘) > ");
		Date transferDate = inputOptionalDate();

		System.out.print("메모 > ");
		String memo = inputString();

		return new TransferDTO(0, fromAccountId, null, toAccountId, null, amount, transferDate, memo, null);
	}

	/*
	 * ========================================================= 출력
	 * =========================================================
	 */

	public void printMember(MemberDTO member) {
		System.out.println();
		System.out.println("===== 회원 정보 =====");
		System.out.println("ID       : " + member.getId());
		System.out.println("이메일   : " + member.getMemberEmail());
		System.out.println("닉네임   : " + member.getMemberNickname());
		System.out.println("권한     : " + member.getMemberRole());
		System.out.println("가입일   : " + member.getCreatedAt());
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

	public void printCategories(List<CategoryDTO> categories) {
		System.out.println();
		System.out.println("===== 카테고리 목록 =====");

		if (categories == null || categories.isEmpty()) {
			System.out.println("등록된 카테고리가 없습니다.");
			return;
		}

		System.out.printf("%-5s %-15s %-10s\n", "ID", "카테고리명", "유형");

		for (CategoryDTO category : categories) {
			System.out.printf("%-5d %-15s %-10s\n", category.getId(), category.getCategoryName(),
					category.getCategoryType());
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

	public void printTransfers(List<TransferDTO> transfers) {
		System.out.println();
		System.out.println("===== 이체 내역 =====");

		if (transfers == null || transfers.isEmpty()) {
			System.out.println("이체 내역이 없습니다.");
			return;
		}

		System.out.printf("%-5s %-15s %-15s %15s %-12s %-20s\n", "ID", "출금 계좌", "입금 계좌", "금액", "날짜", "메모");

		for (TransferDTO transfer : transfers) {
			System.out.printf("%-5d %-15s %-15s %,15d %-12s %-20s\n", transfer.getId(), transfer.getFromAccountName(),
					transfer.getToAccountName(), transfer.getAmount(), transfer.getTransferDate(), transfer.getMemo());
		}
	}

	public void printAccountHistories(List<AccountHistoryDTO> histories) {
		System.out.println();
		System.out.println("===== 전체 내역 =====");

		if (histories == null || histories.isEmpty()) {
			System.out.println("내역이 없습니다.");
			return;
		}

		System.out.printf("%-12s %-20s %-15s %-15s %15s %-12s %-20s\n", "구분", "계좌정보", "상세", "유형", "금액", "날짜", "메모");

		for (AccountHistoryDTO history : histories) {
			System.out.printf("%-12s %-20s %-15s %-15s %,15d %-12s %-20s\n", history.getRecordType(),
					history.getAccountInfo(), history.getDetailInfo(), history.getMoneyType(),
					history.getSignedAmount(), history.getRecordDate(), history.getMemo());
		}
	}

	public void printTotalBalance(long totalBalance) {
		System.out.println();
		System.out.println("===== 내 전체 자산 =====");
		System.out.printf("총 잔액 : %,d원\n", totalBalance);
	}

	public void printMonthlySummary(MonthlySummaryDTO summary) {
		System.out.println();
		System.out.println("===== 월별 수입/지출 요약 =====");
		System.out.printf("수입   : %,d원\n", summary.getIncomeAmount());
		System.out.printf("지출   : %,d원\n", summary.getExpenseAmount());
		System.out.printf("순수익 : %,d원\n", summary.getNetAmount());
	}

	public void printMonthlySummaries(List<MonthlySummaryDTO> summaries) {
		System.out.println();
		System.out.println("===== 월별 수입/지출 목록 =====");

		if (summaries == null || summaries.isEmpty()) {
			System.out.println("조회 결과가 없습니다.");
			return;
		}

		System.out.printf("%-10s %15s %15s %15s\n", "월", "수입", "지출", "순수익");

		for (MonthlySummaryDTO summary : summaries) {
			System.out.printf("%-10s %,15d %,15d %,15d\n", summary.getMonthValue(), summary.getIncomeAmount(),
					summary.getExpenseAmount(), summary.getNetAmount());
		}
	}

	public void printCategorySummaries(List<CategorySummaryDTO> summaries) {
		System.out.println();
		System.out.println("===== 카테고리별 조회 =====");

		if (summaries == null || summaries.isEmpty()) {
			System.out.println("조회 결과가 없습니다.");
			return;
		}

		System.out.printf("%-15s %-10s %15s\n", "카테고리", "유형", "합계");

		for (CategorySummaryDTO summary : summaries) {
			System.out.printf("%-15s %-10s %,15d\n", summary.getCategoryName(), summary.getCategoryType(),
					summary.getTotalAmount());
		}
	}

	/*
	 * ========================================================= 내부 입력 처리
	 * =========================================================
	 */

	private String inputString() {
		return sc.nextLine().trim();
	}

	private int inputInt() {
		while (true) {
			try {
				String input = sc.nextLine().trim();
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.print("숫자로 다시 입력 > ");
			}
		}
	}

	private long inputLong() {
		while (true) {
			try {
				String input = sc.nextLine().trim();
				return Long.parseLong(input);
			} catch (NumberFormatException e) {
				System.out.print("숫자로 다시 입력 > ");
			}
		}
	}

	private Date inputDate() {
		while (true) {
			try {
				String input = sc.nextLine().trim();
				return Date.valueOf(input);
			} catch (IllegalArgumentException e) {
				System.out.print("날짜 형식 오류. 다시 입력(YYYY-MM-DD) > ");
			}
		}
	}

	private Date inputOptionalDate() {
		while (true) {
			try {
				String input = sc.nextLine().trim();

				if (input.isEmpty()) {
					return Date.valueOf(LocalDate.now());
				}

				return Date.valueOf(input);
			} catch (IllegalArgumentException e) {
				System.out.print("날짜 형식 오류. 다시 입력(YYYY-MM-DD, 엔터 시 오늘) > ");
			}
		}
	}
}