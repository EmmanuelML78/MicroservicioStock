package org.emmanuel.microservicesstock.infrastructure.adapters;

import org.emmanuel.microservicesstock.domain.model.Category;
import org.emmanuel.microservicesstock.domain.port.ICategoryRepository;
import org.emmanuel.microservicesstock.infrastructure.mapper.CategoryMapper;
import org.springdoc.core.converters.models.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {

	private final ICategoryCrudRepository categoryCrudRepository;
	private final CategoryMapper mapper;

	public CategoryCrudRepositoryImpl(ICategoryCrudRepository categoryCrudRepository, CategoryMapper mapper) {
		this.categoryCrudRepository = categoryCrudRepository;
		this.mapper = mapper;
	}

	@Override
	public Category save(Category category) {
		return mapper.toCategory(categoryCrudRepository.save(mapper.toCategoryEntity(category)));
	}

	@Override
	public Optional<Category> findByName(String name) {
		return categoryCrudRepository.findByName(name).map(mapper::toCategory);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryCrudRepository.findAll(pageable).map(mapper::toCategory);
	}

	@Override
	public Category findById(Long id) {
		return mapper.toCategory(categoryCrudRepository.findById(id).orElseThrow(
				()-> new RuntimeException("Categoría con id: "+id+ " no existe")
		));
	}


}
