package org.emmanuel.microservicesstock.domain.port;

import org.emmanuel.microservicesstock.domain.model.Category;

import java.util.Optional;

public interface ICategoryRepository {
	Category save(Category category);
	Category findById(Long id);
	Optional<Category> findByName(String name);
}
