package pharmacy.administration.entities;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category_image")
public class CategoryImage {

    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, name = "id_category_image", columnDefinition = "int4")	
	private Long id_category_image;
	
	// @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

	@Column(name = "image", length = 120)
	private String image;
		
	// @NotEmpty( message = "es obligatorio" )
	// @NotNull( message = "no puede ser nulo" )
	// @Size( min = 1, max = 1, message = "tiene que poseer un caracter")
	@Column(name = "state", length = 1)
	private String state;
    
}
