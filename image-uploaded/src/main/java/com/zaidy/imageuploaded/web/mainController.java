package com.zaidy.imageuploaded.web;

import com.zaidy.imageuploaded.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Controller
public class mainController
{
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    @GetMapping("/")
    public String index(Model model)
    {
        Person person =new Person();
        model.addAttribute("person",person);
        return "index";
    }

    @PostMapping("/save")
    public  void saveFile(@RequestParam("image")MultipartFile file, @ModelAttribute("person") Person person)
    {
        log.info(file.getOriginalFilename());
    }
}
