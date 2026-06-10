package com.app.wallet.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 계좌 이체 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferDTO {
	private int id;
	private int fromAccountId;
	private String fromAccountName;
	private int toAccountId;
	private String toAccountName;
	private long amount;
	private Date transferDate;
	private String memo;
	private Date createdAt;
}