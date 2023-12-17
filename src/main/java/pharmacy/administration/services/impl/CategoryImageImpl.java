package pharmacy.administration.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacy.administration.entities.CategoryImage;
import pharmacy.administration.repositories.ICategoryImageRepository;

import pharmacy.administration.services.ICategoryImageServices;



@Service
public class CategoryImageImpl implements ICategoryImageServices {

    @Autowired
    private ICategoryImageRepository categoryImageRepository;

    @Override
    public List<CategoryImage> listCategoryImgById( Long id_category ) {
        return categoryImageRepository.listCategoryImgById(id_category);
    }

    @Override
    public CategoryImage saveCategoryImg(CategoryImage categoryImg) {
        return categoryImageRepository.save(categoryImg);
    }

    @Override
    public void deleteCategoryImgById(Long id_category_image) {
        categoryImageRepository.deleteById( id_category_image );
    }    

}
