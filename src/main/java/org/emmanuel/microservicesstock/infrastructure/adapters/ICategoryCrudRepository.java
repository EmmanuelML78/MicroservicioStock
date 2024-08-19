package org.emmanuel.microservicesstock.infrastructure.adapters;

import org.emmanuel.microservicesstock.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryCrudRepository extends JpaRepository<CategoryEntity, Long> {
	Optional<CategoryEntity> findByName(String name);
}