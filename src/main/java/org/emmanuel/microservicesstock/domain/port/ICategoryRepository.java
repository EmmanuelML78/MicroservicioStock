package org.emmanuel.microservicesstock.domain.port;

import org.emmanuel.microservicesstock.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {
	Category save(Category category);
	Category findById(Long id);
	Optional<Category> findByName(String name);
	Page<Category> findAll(Pageable pageable);
}
