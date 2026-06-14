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
public class MemberDTO {
	private int id;
	private String memberEmail;
	private String memberPassword;
	private String memberNickname;
	private String memberRole;
	private Date createdAt;
	private Date updatedAt;
}
