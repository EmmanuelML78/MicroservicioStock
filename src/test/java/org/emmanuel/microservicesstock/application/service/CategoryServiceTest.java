package org.emmanuel.microservicesstock.application.service;

import org.emmanuel.microservicesstock.domain.model.Category;
import org.emmanuel.microservicesstock.domain.port.ICategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;
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

	@Test
	void testFindAllPaginatedAndSorted() {
		Category category1 = new Category(1L, "Books", "Libros y novelas");
		Category category2 = new Category(2L, "Electronics", "Artículos electrónicos y gadgets");
		List<Category> categories = Arrays.asList(category1, category2);

		Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "name"));
		Page<Category> page = new PageImpl<>(categories, pageable, categories.size());

		when(categoryRepository.findAll(pageable)).thenReturn(page);

		Page<Category> result = categoryService.findAllPaginated(0, 2, "asc");

		assertNotNull(result);
		assertEquals(2, result.getTotalElements());
		assertEquals("Books", result.getContent().get(0).getName());
		assertEquals("Electronics", result.getContent().get(1).getName());
	}
}
