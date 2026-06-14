package com.app.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MonthlySummaryDTO {
	private String monthValue;
	private long incomeAmount;
	private long expenseAmount;
	private long netAmount;
}
