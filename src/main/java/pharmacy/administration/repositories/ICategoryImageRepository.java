package pharmacy.administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pharmacy.administration.entities.CategoryImage;

public interface ICategoryImageRepository extends CrudRepository<CategoryImage, Long> {

    @Query("SELECT c FROM CategoryImage c WHERE c.category.id_category = ?1")
    public List<CategoryImage> listCategoryImgById( Long id_category );


    
}
