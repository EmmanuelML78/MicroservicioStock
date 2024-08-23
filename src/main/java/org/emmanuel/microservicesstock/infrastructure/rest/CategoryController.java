package org.emmanuel.microservicesstock.infrastructure.rest;

import jakarta.validation.Valid;
import org.emmanuel.microservicesstock.application.service.CategoryService;
import org.emmanuel.microservicesstock.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping
	public ResponseEntity<Category> save(@RequestBody @Valid Category category) {
		return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<Category>> findAllPaginated(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "asc") String sortDirection) {

		Page<Category> categories = categoryService.findAllPaginated(page, size, sortDirection);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
}
