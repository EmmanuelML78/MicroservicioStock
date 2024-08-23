package org.emmanuel.microservicesstock.infrastructure.mapper;

import org.emmanuel.microservicesstock.domain.model.Category;
import org.emmanuel.microservicesstock.infrastructure.entity.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	Category toCategory(CategoryEntity categoryEntity);

	Iterable<Category> toCategoryList( Iterable<CategoryEntity> categoryEntities);

	@InheritInverseConfiguration
	CategoryEntity toCategoryEntity(Category category);
}
