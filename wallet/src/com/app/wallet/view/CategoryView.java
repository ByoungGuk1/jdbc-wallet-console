package com.app.wallet.view;

import java.util.List;

import com.app.wallet.dto.CategoryDTO;
import com.app.wallet.dto.MemberDTO;

public class CategoryView {

	private final View view;

	CategoryView(View view) {
		this.view = view;
	}

	public CategoryDTO inputCategoryForInsert(MemberDTO loginMember) {
		System.out.println();
		System.out.println("===== 카테고리 등록 =====");

		System.out.print("카테고리명 > ");
		String categoryName = view.inputString();

		String categoryType = inputCategoryType();

		return CategoryDTO.builder().memberId(loginMember.getId()).categoryName(categoryName).categoryType(categoryType)
				.build();
	}

	public CategoryDTO inputCategoryForUpdate(MemberDTO loginMember) {
		System.out.println();
		System.out.println("===== 카테고리 수정 =====");

		int categoryId = view.inputCategoryId();

		System.out.print("카테고리명 > ");
		String categoryName = view.inputString();

		String categoryType = inputCategoryType();

		return CategoryDTO.builder().id(categoryId).memberId(loginMember.getId()).categoryName(categoryName)
				.categoryType(categoryType).build();
	}

	public CategoryDTO inputCategoryCondition(MemberDTO loginMember) {
		int categoryId = view.inputCategoryId();

		return CategoryDTO.builder().id(categoryId).memberId(loginMember.getId()).build();
	}

	public CategoryDTO inputCategoryTypeCondition(MemberDTO loginMember) {
		String type = inputCategoryType();

		return CategoryDTO.builder().memberId(loginMember.getId()).categoryType(type).build();
	}

	private String inputCategoryType() {
		while (true) {
			System.out.print("카테고리 유형(INCOME/EXPENSE) > ");
			String type = view.inputString().toUpperCase();

			if ("INCOME".equals(type) || "EXPENSE".equals(type)) {
				return type;
			}

			view.showError("INCOME 또는 EXPENSE만 입력 가능합니다.");
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
}
