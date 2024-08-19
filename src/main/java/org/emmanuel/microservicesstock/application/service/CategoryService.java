package org.emmanuel.microservicesstock.application.service;

import org.emmanuel.microservicesstock.domain.model.Category;
import org.emmanuel.microservicesstock.domain.port.ICategoryRepository;

import java.util.Optional;

public class CategoryService {

	private final ICategoryRepository iCategoryRepository;

	public CategoryService(ICategoryRepository iCategoryRepository) {
		this.iCategoryRepository = iCategoryRepository;
	}

	public Category save(Category category) {
		Optional<Category> categoryOptional = iCategoryRepository.findByName(category.getName());
		if (categoryOptional.isPresent()) {
			throw new IllegalArgumentException("Category name already exists: " + category.getName());
		}
		return iCategoryRepository.save(category);
	}

	public Category findById(Long id) {
		return iCategoryRepository.findById(id);
	}
}
