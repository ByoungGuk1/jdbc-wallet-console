package com.app.wallet.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 거래 내역과 이체 내역을 하나의 목록으로 출력하기 위한 조회 전용 DTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountHistoryDTO {
	private String recordType; // TRANSACTION, TRANSFER
	private int recordId; // 거래 ID 또는 이체 ID
	private String accountInfo; // 계좌명 또는 출금계좌 -> 입금계좌
	private String detailInfo; // 카테고리명 또는 계좌이체
	private String moneyType; // INCOME, EXPENSE, TRANSFER_IN, TRANSFER_OUT, TRANSFER
	private long signedAmount; // 수입 +, 지출 -, 이체 출금 -, 이체 입금 +
	private Date recordDate; // 거래일 또는 이체일
	private String memo;
}