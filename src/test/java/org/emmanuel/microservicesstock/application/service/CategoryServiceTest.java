package org.emmanuel.microservicesstock.application.service;

import org.emmanuel.microservicesstock.domain.model.Category;
import org.emmanuel.microservicesstock.domain.port.ICategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	@Mock
	private ICategoryRepository categoryRepository;

	@InjectMocks
	private CategoryService categoryService;

	@Test
	void testGetCategoryById() {

		Category category = new Category(1L, "Electronics", "Artículos electrónicos y gadgets");
		when(categoryRepository.findById(1L)).thenReturn(category);

		Category result = categoryService.findById(1L);

		assertNotNull(result);
		assertEquals("Electronics", result.getName());
	}

	@Test
	void testCreateCategory() {

		Category category = new Category(1L, "Electronics", "Artículos electrónicos y gadgets");
		category.setName("Electronics");
		when(categoryRepository.findByName("Electronics")).thenReturn(Optional.of(category));

		Optional<Category> result = categoryRepository.findByName("Electronics");
		assertEquals("Electronics", result.get().getName());
	}
}
