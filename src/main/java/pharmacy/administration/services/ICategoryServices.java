package pharmacy.administration.services;

import java.util.List;
import java.util.Optional;

import pharmacy.administration.entities.Category;

public interface ICategoryServices {

    public List<Category> listActiveCategories();

    public Optional<Category> findCategoryById ( Integer id_category );
    
    public Category saveCategory( Category category );

    public Category updateCategory( Category category );

    public void deleteCategoryById ( Integer id_category );
    
}
