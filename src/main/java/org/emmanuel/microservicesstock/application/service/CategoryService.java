package org.emmanuel.microservicesstock.application.service;

import org.emmanuel.microservicesstock.domain.exception.CategoryNotFoundException;
import org.emmanuel.microservicesstock.domain.model.Category;
import org.emmanuel.microservicesstock.domain.port.ICategoryRepository;

import java.util.Optional;

public class CategoryService {

	private final ICategoryRepository iCategoryRepository;

	public CategoryService(ICategoryRepository iCategoryRepository) {
		this.iCategoryRepository = iCategoryRepository;
	}

	public Category save(Category category) {

		String validatedName = Optional.ofNullable(category.getName())
				.filter(name -> !name.trim().isEmpty())
				.orElseThrow(() -> new CategoryNotFoundException("Category name is required."));

		String validatedDescription = Optional.ofNullable(category.getDescription())
				.filter(description -> !description.trim().isEmpty())
				.orElseThrow(() -> new CategoryNotFoundException("Category description is required."));

		category.setName(validatedName);
		category.setDescription(validatedDescription);

		iCategoryRepository.findByName(validatedName)
				.ifPresent(existingCategory -> {
					throw new IllegalArgumentException("Category name already exists: " + validatedName);
				});

		return iCategoryRepository.save(category);
	}

	public Category findById(Long id) {
		return iCategoryRepository.findById(id);
	}
}
