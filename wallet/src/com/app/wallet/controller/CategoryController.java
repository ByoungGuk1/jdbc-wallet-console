package com.app.wallet.controller;

import java.util.List;

import com.app.wallet.dto.CategoryDTO;
import com.app.wallet.dto.MemberDTO;
import com.app.wallet.service.CategoryService;
import com.app.wallet.view.View;

public class CategoryController {

	private final View view;
	private final CategoryService categoryService = new CategoryService();

	public CategoryController(View view) {
		this.view = view;
	}

	public void runCategoryMenu(MemberDTO loginMember) {
		boolean running = true;

		while (running) {
			int menu = view.menu().showCategoryMenu();

			try {
				switch (menu) {
				case 1:
					insertCategory(loginMember);
					break;
				case 2:
					selectCategories(loginMember);
					break;
				case 3:
					selectCategoriesByType(loginMember);
					break;
				case 4:
					updateCategory(loginMember);
					break;
				case 5:
					deleteCategory(loginMember);
					break;
				case 0:
					running = false;
					break;
				default:
					view.showError("잘못된 메뉴입니다.");
				}
			} catch (RuntimeException e) {
				view.showError(e.getMessage());
			}
		}
	}

	private void insertCategory(MemberDTO loginMember) {
		CategoryDTO category = view.category().inputCategoryForInsert(loginMember);
		boolean result = categoryService.createCategory(category);

		if (result) {
			view.showMessage("카테고리가 등록되었습니다.");
		} else {
			view.showError("카테고리 등록에 실패했습니다.");
		}
	}

	private void selectCategories(MemberDTO loginMember) {
		List<CategoryDTO> categories = categoryService.getCategories(loginMember.getId());
		view.category().printCategories(categories);
	}

	private void selectCategoriesByType(MemberDTO loginMember) {
		CategoryDTO condition = view.category().inputCategoryTypeCondition(loginMember);
		List<CategoryDTO> categories = categoryService.getCategoriesByType(condition);
		view.category().printCategories(categories);
	}

	private void updateCategory(MemberDTO loginMember) {
		CategoryDTO category = view.category().inputCategoryForUpdate(loginMember);
		boolean result = categoryService.updateCategory(category);

		if (result) {
			view.showMessage("카테고리가 수정되었습니다.");
		} else {
			view.showError("카테고리 수정에 실패했습니다.");
		}
	}

	private void deleteCategory(MemberDTO loginMember) {
		CategoryDTO condition = view.category().inputCategoryCondition(loginMember);
		boolean result = categoryService.deleteCategory(condition);

		if (result) {
			view.showMessage("카테고리가 삭제되었습니다.");
		} else {
			view.showError("카테고리 삭제에 실패했습니다.");
		}
	}
}
