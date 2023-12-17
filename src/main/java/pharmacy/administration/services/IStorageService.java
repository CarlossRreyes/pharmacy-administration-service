package pharmacy.administration.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {

    void init();
    
    String store( MultipartFile file );

    Resource loadAsResource( String filename );
}
