package com.zaidy.loadsaveimagespringangular.controller;

import com.zaidy.loadsaveimagespringangular.model.Product;
import com.zaidy.loadsaveimagespringangular.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "image")
public class MainController
{
    public static String UPLOAD_DIRECTORY = System.getProperty("user.home") + "/uploads/";
    @Autowired
    ProductRepo productRepo;
    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public  void save(@RequestPart Product product, @RequestPart MultipartFile file) throws IOException {
        product.setImage(file.getOriginalFilename().getBytes());
        Files.write(Paths.get(UPLOAD_DIRECTORY+product.getName()),file.getBytes());
        productRepo.save(product);
    }


    //@PostMapping("/upload/image")

    @PostMapping("/upload")
    public ResponseEntity<Product> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Product img = new Product();
        img.setImage(compressBytes(file.getBytes()));
        img.setName(file.getOriginalFilename());
        img.setType(file.getContentType());

        productRepo.save(img);
        return new ResponseEntity<Product>(img,HttpStatus.OK);
    }

    @GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Product p=productRepo.findById(id).get();
      //  return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()));

        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/images/photo/"+p.getName()));

    }
//    @PostMapping(path = "/uploadPhoto/{id}")
//    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{
//        Product p=productRepo.findById(id).get();
//        p.setName(file.getOriginalFilename());
//        Files.write(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()),file.getBytes());
//        productRepository.save(p);
//    }
    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }
    @GetMapping(path = { "/get/{imageName}" })
    public Product getImage(@PathVariable("imageName") String imageName) throws IOException {

        final Optional<Product> retrievedImage = productRepo.findByName(imageName);
        Product img = new Product();
        img.setName(retrievedImage.get().getName());
        img.setType(retrievedImage.get().getType());
        img.setImage(decompressBytes(retrievedImage.get().getImage()));

        return img;
    }



























    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
