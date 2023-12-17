package pharmacy.administration.services;

import java.util.List;

import pharmacy.administration.entities.CategoryImage;

public interface ICategoryImageServices {

    // public List<Category> listActiveCategories();

    public List<CategoryImage> listCategoryImgById ( Long id_category );
    
    public CategoryImage saveCategoryImg( CategoryImage categoryImg );

    // public Category updateCategory( Category category );

    public void deleteCategoryImgById ( Long id_category_image );
    
}
