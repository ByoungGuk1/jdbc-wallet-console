package com.app.wallet.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 거래 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDTO {
	private int id;
	private int accountId;
	private String accountName;
	private int categoryId;
	private String categoryName;
	private String transactionType;
	private long amount;
	private Date transactionDate;
	private String memo;
	private Date createdAt;
	private Date updatedAt;
}