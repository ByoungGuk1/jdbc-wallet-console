package com.app.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 카테고리별 합계 조회 전용 DTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategorySummaryDTO {
	private String categoryName;
	private String categoryType; // INCOME, EXPENSE
	private long totalAmount;
}