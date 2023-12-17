package pharmacy.administration.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
// import lombok.RequiredArgsConstructor;
import pharmacy.administration.entities.Category;
import pharmacy.administration.repositories.ICategoryRepository;
import pharmacy.administration.services.ICategoryServices;


@Service
public class CategoryImpl implements ICategoryServices{

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<Category> listActiveCategories() {
        return this.categoryRepository.listActiveCategories();
    }

    @Override
    public Category saveCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save( category );
    }

    @Override
    public Optional<Category> findCategoryById(Integer id_category) {
        return this.categoryRepository.findById( id_category );
    }

    @Override
    public void deleteCategoryById(Integer id_category) {
        this.categoryRepository.deleteById(id_category);
    }

    

}
