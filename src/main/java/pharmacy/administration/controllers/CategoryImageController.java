package pharmacy.administration.controllers;


import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import jakarta.validation.Valid;

import pharmacy.administration.entities.CategoryImage;
import pharmacy.administration.services.ICategoryImageServices;

import pharmacy.administration.utils.RequestResponse;
import pharmacy.administration.utils.ResponseMessage;

@RestController
@RequestMapping("/api/pharmacy-administration/category/img")
public class CategoryImageController {

    @Autowired
	private ICategoryImageServices categoryImgServices;
	
	@GetMapping("/byId/{id_category}")
	public ResponseEntity<?> getAllImageById( @PathVariable Long id_category ){	
        List<CategoryImage> categoryImgList = new ArrayList<>();        
        try {
            categoryImgList = categoryImgServices.listCategoryImgById( id_category );
        } catch (DataAccessException e) {
            return new ResponseEntity<RequestResponse>(
                new RequestResponse(false, ResponseMessage.ERROR_QUERY, null, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage())), 
                HttpStatus.INTERNAL_SERVER_ERROR );           
        }
        if( categoryImgList == null || categoryImgList.size() == 0 ){            
            return new ResponseEntity<RequestResponse>(
                new RequestResponse(false, ResponseMessage.NO_DATA_FOUND, null, null), 
                HttpStatus.OK );    
        }
        return new ResponseEntity<RequestResponse>( new RequestResponse(true, ResponseMessage.DATA_SUCCESSFULLY_RECOVERED, null, categoryImgList), HttpStatus.OK);
	}

    

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory( @RequestBody List<CategoryImage> categoriesImg ){
        // if( result.hasErrors() ){
        //     List<String> errors = result.getFieldErrors().stream()
        //                 .map( err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
        //                 .collect( Collectors.toList());            
        //     return new ResponseEntity<RequestResponse>(
        //         new RequestResponse(false, ResponseMessage.INVALID_FORM, errors.toString(), null), HttpStatus.BAD_REQUEST);
        // }
            System.out.println("DATA DEL IMAGEN: " + categoriesImg);
        try {
            for (CategoryImage categoryImage : categoriesImg) {                
                this.categoryImgServices.saveCategoryImg(categoryImage);
            }
            // this.categoryImgServices.saveCategoryImg(categoryImg);
        } catch (DataAccessException e) {            
            return new ResponseEntity<RequestResponse>( 
                new RequestResponse(false, ResponseMessage.ERROR_QUERY, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()), null), 
                HttpStatus.INTERNAL_SERVER_ERROR ); 
        }   
        return new ResponseEntity<RequestResponse>(new RequestResponse(true, ResponseMessage.DATA_SUCCESSFULLY_RECORDED, null, null), HttpStatus.CREATED);   
    }
    

    // @PostMapping("/save")
    // public ResponseEntity<?> saveCategory(@Valid @RequestBody Category category, BindingResult result ){
    //     if( result.hasErrors() ){
    //         List<String> errors = result.getFieldErrors().stream()
    //                     .map( err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
    //                     .collect( Collectors.toList());            
    //         return new ResponseEntity<RequestResponse>(
    //             new RequestResponse(false, ResponseMessage.INVALID_FORM, errors.toString(), null), HttpStatus.BAD_REQUEST);
    //     }

    //     try {
    //         this.categoryServices.saveCategory(category);
    //     } catch (DataAccessException e) {            
    //         return new ResponseEntity<RequestResponse>( 
    //             new RequestResponse(false, ResponseMessage.ERROR_QUERY, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()), null), 
    //             HttpStatus.INTERNAL_SERVER_ERROR ); 
    //     }   
    //     return new ResponseEntity<RequestResponse>(new RequestResponse(true, ResponseMessage.DATA_SUCCESSFULLY_RECORDED, null, null), HttpStatus.CREATED);   


    // }

    // @PutMapping("/update/{id_category}")
    // public ResponseEntity<?> updateCategory( @PathVariable Integer id_category, @RequestBody Category category ){
    //     RequestResponse response = new RequestResponse();
    //     // Category responseCategory = new Category(); 
    //     Optional<Category> optionalCategory = this.categoryServices.findCategoryById(id_category);   
        
    //     if(!(optionalCategory.isPresent())){
    //         response.setMessage("No se ha encontrado esta categoría.");
    //         return new ResponseEntity<RequestResponse>(
    //             new RequestResponse(false, "No se ha encontrado esta categoría.", null, null), 
    //             HttpStatus.BAD_REQUEST );
    //     }
    //     try {
    //         this.categoryServices.updateCategory(category);            
    //     } catch (DataAccessException e) {
    //         return new ResponseEntity<RequestResponse>(
    //             new RequestResponse(false, ResponseMessage.ERROR_QUERY, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()), null), 
    //             HttpStatus.INTERNAL_SERVER_ERROR );            
    //     }
    //     return new ResponseEntity<RequestResponse>( new RequestResponse(false, ResponseMessage.DATA_SUCCESSFULLY_UPDATED, null, null), HttpStatus.OK);
    // }

    @PostMapping("/update")
    public ResponseEntity<?> updateCategoryImgById(  @RequestBody List<CategoryImage> listCategoryImg ){
        try {
            for (CategoryImage c : listCategoryImg) {
                this.categoryImgServices.deleteCategoryImgById( c.getId_category_image() );                
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<RequestResponse>( 
                new RequestResponse(false, ResponseMessage.ERROR_QUERY, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()), null),
                HttpStatus.INTERNAL_SERVER_ERROR ); 
        }
        return new ResponseEntity<RequestResponse>( new RequestResponse(false, ResponseMessage.DATA_SUCCESSFULLY_UPDATED, null, null), HttpStatus.OK);
    }

}
