package org.emmanuel.microservicesstock.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@Entity
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El nombre de la categoría es requerido.")
	@Size(max = 50, message = "El nombre de la categoría no puede exceder los 50 caracteres.")
	private String name;

	@NotBlank(message = "La descripción de la categoría es requerida.")
	@Size(max = 90, message = "La descripción de la categoría no puede exceder los 90 caracteres.")
	private String description;
}
