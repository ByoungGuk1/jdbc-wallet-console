package com.app.wallet.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 카테고리 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO {
	private int id;
	private int memberId;
	private String categoryName;
	private String categoryType;
	private Date createdAt;
	private Date updatedAt;
}