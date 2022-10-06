package com.example.backendupload.controller;


import com.example.backendupload.model.Person;
import com.example.backendupload.repository.UsersRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api")
public class UserController {
    @Autowired
    UsersRepo usersRepo;

 @PostMapping("/users")
    public Person createUser(@RequestParam(name = "avatar") MultipartFile avatar) throws Exception {
    String fileName= StringUtils.cleanPath(avatar.getOriginalFilename());
     Person users = new Person();
     if(fileName.contains(".")) throw new Exception("File contains invalid"+fileName);
     usersRepo.save(users);

     //-------------------------see what this methode done

    String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().
            path("/download").path(avatar.getOriginalFilename()).toUriString();
     return users;
 }

    @PostMapping(value = "/saveUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> createUser(@RequestParam(name = "avatar") MultipartFile avatar,

                                                 @RequestPart(name = "name") String name)  throws JsonProcessingException, IOException
    {
        log.info("we can..........................................\n\n" +
                "thats greaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaat");
                 Person users =new Person();
                 users.setName(name);
                 users.setPhoto(avatar.getOriginalFilename());

                 log.info(name.toString());
                 log.info("\nnnnnn");
                 log.info(avatar.getOriginalFilename());
                 usersRepo.save(users);
        Files.write(Paths.get(System.getProperty("user.home")+"/Documents/"+avatar.getOriginalFilename()),avatar.getBytes());
        return ResponseEntity.ok(HttpStatus.OK);

    }

//    @GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
//    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
//        Product p=productRepository.findById(id).get();
//        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()));
//    }
//    @PostMapping(path = "/uploadPhoto/{id}")
//    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{
//        Product p=productRepository.findById(id).get();
//        p.setPhotoName(file.getOriginalFilename());
//        Files.write(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()),file.getBytes());
//        productRepository.save(p);
//    }
}
//===================================================
/*
*
* onSelectedFile(event) {
    this.selectedFiles=event.target.files;
  }
----------------------------------------------------------
uploadPhoto() {
    this.progress = 0;
    this.currentFileUpload = this.selectedFiles.item(0)
    this.catService.uploadPhotoProduct(this.currentFileUpload, this.currentProduct.id).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        //console.log(this.router.url);
        //this.getProducts(this.currentRequest);
        //this.refreshUpdatedProduct();
        this.currentTime=Date.now();
      }
    },err=>{
      alert("Problème de chargement");
    })
    this.selectedFiles = undefined
  }
========================================================
uploadPhotoProduct(file: File, idProduct): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
    formdata.append('file', file);
    const req = new HttpRequest('POST', this.host+'/uploadPhoto/'+idProduct, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }
==========================================================
<input type="file" (change)="onSelectedFile($event)">
        		    <button class="btn btn-success" (click)="uploadPhoto()">Upload</button>
            {{progress}}
---------------------------------------------------------
* */