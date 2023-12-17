package pharmacy.administration.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import pharmacy.administration.services.IStorageService;

@RestController
@RequestMapping("/api/pharmacy-administration/media")
@AllArgsConstructor
public class MediaController {
    
    private final IStorageService iStorageService;
    private final HttpServletRequest httpServletRequest;

    @PostMapping("/upload")
    public List<Map<String, String>> uploadFiles(@RequestPart("file") List<MultipartFile> multipartFiles) {
        List<Map<String, String>> responseList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String path = iStorageService.store(multipartFile);
            String host = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "");
            String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();
            Map<String, String> response = Map.of("url", url);
            responseList.add(response);
        }
        return responseList;
    }
    // @PostMapping("/upload")
    // public Map<String, String> uploadFile( @RequestParam("file") MultipartFile multipartFile ){
    //     System.out.println("Archivo: " + multipartFile);
    //     String path = iStorageService.store(multipartFile);
    //     String host = httpServletRequest.getRequestURL().toString().replace( httpServletRequest.getRequestURI(), "");
    //     String url = ServletUriComponentsBuilder
    //         .fromHttpUrl(host)
    //         .path("/media/")
    //         .path(path)
    //         .toUriString();
    //     return Map.of("url", url );
    // }

    @GetMapping("{filename:.*}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename ) throws IOException{
        Resource file = iStorageService.loadAsResource(filename);
        String contenType = Files.probeContentType( file.getFile().toPath() );
        return ResponseEntity
            .ok()
            .header( HttpHeaders.CONTENT_TYPE,  contenType )
            .body( file );
    }
}
