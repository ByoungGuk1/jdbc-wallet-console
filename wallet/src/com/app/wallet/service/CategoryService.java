package com.app.wallet.service;

import java.util.List;

import com.app.wallet.dao.CategoryDAO;
import com.app.wallet.dto.CategoryDTO;

public class CategoryService {

	private final CategoryDAO categoryDAO = new CategoryDAO();

	public boolean createCategory(CategoryDTO category) {
		return categoryDAO.insertCategory(category) == 1;
	}

	public List<CategoryDTO> getCategories(int memberId) {
		return categoryDAO.selectCategoriesByMemberId(memberId);
	}

	public List<CategoryDTO> getCategoriesByType(CategoryDTO condition) {
		return categoryDAO.selectCategoriesByType(condition);
	}

	public boolean updateCategory(CategoryDTO category) {
		return categoryDAO.updateCategory(category) == 1;
	}

	public boolean deleteCategory(CategoryDTO condition) {
		return categoryDAO.deleteCategory(condition) == 1;
	}
}
