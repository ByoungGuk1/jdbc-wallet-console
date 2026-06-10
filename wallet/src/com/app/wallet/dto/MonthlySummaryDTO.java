package com.app.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 월별 수입/지출 요약 조회 전용 DTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MonthlySummaryDTO {
	private String monthValue; // YYYY-MM
	private long incomeAmount; // 수입 합계
	private long expenseAmount; // 지출 합계
	private long netAmount; // 수입 - 지출
}