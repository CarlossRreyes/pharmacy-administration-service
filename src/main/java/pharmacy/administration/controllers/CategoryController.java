package pharmacy.administration.controllers;


import java.util.ArrayList;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import pharmacy.administration.entities.Category;
import pharmacy.administration.services.ICategoryServices;
import pharmacy.administration.utils.RequestResponse;
import pharmacy.administration.utils.ResponseMessage;

@RestController
@RequestMapping("/api/pharmacy-administration/category")
public class CategoryController {

    @Autowired
	private ICategoryServices categoryServices;
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllCategorias(){	
        
        List<Category> categoryList = new ArrayList<>();        
        try {
            categoryList = categoryServices.listActiveCategories();
        } catch (DataAccessException e) {
            return new ResponseEntity<RequestResponse>(
                new RequestResponse(false, ResponseMessage.ERROR_QUERY, null, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage())), 
                HttpStatus.INTERNAL_SERVER_ERROR );           
        }
        if( categoryList == null || categoryList.size() == 0 ){            
            return new ResponseEntity<RequestResponse>(
                new RequestResponse(false, ResponseMessage.NO_DATA_FOUND, null, null), 
                HttpStatus.OK );    
        }
        return new ResponseEntity<RequestResponse>( new RequestResponse(true, ResponseMessage.DATA_SUCCESSFULLY_RECOVERED, null, categoryList), HttpStatus.OK);
	}
    

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@Valid @RequestBody Category category, BindingResult result ){
        Category newCategory = null;
        if( result.hasErrors() ){
            List<String> errors = result.getFieldErrors().stream()
                        .map( err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                        .collect( Collectors.toList());            
            return new ResponseEntity<RequestResponse>(
                new RequestResponse(false, ResponseMessage.INVALID_FORM, errors.toString(), null), HttpStatus.BAD_REQUEST);
        }

        try {
            newCategory =  this.categoryServices.saveCategory(category);
        } catch (DataAccessException e) {            
            return new ResponseEntity<RequestResponse>( 
                new RequestResponse(false, ResponseMessage.ERROR_QUERY, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()), null), 
                HttpStatus.INTERNAL_SERVER_ERROR ); 
        }   
        return new ResponseEntity<RequestResponse>(new RequestResponse(true, ResponseMessage.DATA_SUCCESSFULLY_RECORDED, null, newCategory), HttpStatus.CREATED);   


    }

    @PutMapping("/update/{id_category}")
    public ResponseEntity<?> updateCategory( @PathVariable Integer id_category, @RequestBody Category category ){
        // RequestResponse response = new RequestResponse();

        Category responseCategory = new Category(); 
        Optional<Category> optionalCategory = this.categoryServices.findCategoryById(id_category);   
        
        if(!(optionalCategory.isPresent())){
            // response.setMessage("No se ha encontrado esta categoría.");
            return new ResponseEntity<RequestResponse>(
                new RequestResponse(false, "No se ha encontrado esta categoría.", null, null), 
                HttpStatus.BAD_REQUEST );
        }
        try {
            responseCategory = this.categoryServices.updateCategory(category);            
        } catch (DataAccessException e) {
            return new ResponseEntity<RequestResponse>(
                new RequestResponse(false, ResponseMessage.ERROR_QUERY, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()), null), 
                HttpStatus.INTERNAL_SERVER_ERROR );            
        }
        return new ResponseEntity<RequestResponse>( new RequestResponse(true, ResponseMessage.DATA_SUCCESSFULLY_UPDATED, null, responseCategory), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id_category}")
    public ResponseEntity<?> deleteCategory( @PathVariable Integer id_category ){
        try {
            this.categoryServices.deleteCategoryById(id_category);
        } catch (DataAccessException e) {
            return new ResponseEntity<RequestResponse>( 
                new RequestResponse(false, ResponseMessage.ERROR_QUERY, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()), null),
                HttpStatus.INTERNAL_SERVER_ERROR ); 
        }
        return new ResponseEntity<RequestResponse>( new RequestResponse(false, ResponseMessage.DELETED_RECORD, null, null), HttpStatus.OK);   
    }

}
