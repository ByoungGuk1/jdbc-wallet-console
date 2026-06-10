package com.app.wallet.controller;

import com.app.wallet.view.View;

public class CategoryController {

	private final View view;

	public CategoryController(View view) {
		this.view = view;
	}

	public void insertCategory(int memberId) {
		view.showMessage("카테고리 등록 기능 준비 중");
	}

	public void selectCategories(int memberId) {
		view.showMessage("카테고리 목록 조회 기능 준비 중");
	}

	public void updateCategory(int memberId) {
		view.showMessage("카테고리 수정 기능 준비 중");
	}

	public void deleteCategory(int memberId) {
		view.showMessage("카테고리 삭제 기능 준비 중");
	}
}