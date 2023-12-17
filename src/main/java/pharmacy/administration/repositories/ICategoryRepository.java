package pharmacy.administration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pharmacy.administration.entities.Category;

public interface ICategoryRepository extends CrudRepository<Category, Integer> {

    @Query("SELECT c FROM Category c")
    public List<Category> listActiveCategories();
    
}
