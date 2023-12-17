package pharmacy.administration.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {

	

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, name = "id_category", columnDefinition = "int4")	
	private Long id_category;
	
	
	@NotEmpty( message = "es obligatorio" )
	@NotNull( message = "no puede ser nulo" )
    @Column(name = "name", length = 40)
	private String name;
		
	@NotEmpty( message = "es obligatorio" )
	@NotNull( message = "no puede ser nulo" )
	@Column(name = "description", length = 350)
	private String description;

	// @Column(name = "image", length = 120)
	// private String image;
		
	@NotEmpty( message = "es obligatorio" )
	@NotNull( message = "no puede ser nulo" )
	@Size( min = 1, max = 1, message = "tiene que poseer un caracter")
	@Column(name = "state", length = 1)
	private String state;

    
    
}
