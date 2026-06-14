package com.app.wallet.dto;

import java.sql.Date;

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
public class AccountDTO {
	private int id;
	private int memberId;
	private String bankName;
	private String accountName;
	private String accountNumber;
	private long balance;
	private String accountStatus;
	private Date createdAt;
	private Date updatedAt;
}
