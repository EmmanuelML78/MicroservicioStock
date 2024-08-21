package org.emmanuel.microservicesstock.infrastructure.config;

import org.emmanuel.microservicesstock.application.service.CategoryService;
import org.emmanuel.microservicesstock.domain.port.ICategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	@Bean
	public CategoryService categoryService(ICategoryRepository iCategoryRepository) {
		return new CategoryService(iCategoryRepository);
	}
}
