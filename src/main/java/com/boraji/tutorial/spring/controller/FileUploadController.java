package com.boraji.tutorial.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author imssbora
 */
@Controller
public class FileUploadController {

   @RequestMapping(value = "/")
   public String fileUploadForm(Model model) {
      return "fileUploadForm";
   }

   // Handling file upload request
   @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
   public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file)
         throws IOException {

      // Save file on system
      if (!file.getOriginalFilename().isEmpty()) {
         BufferedOutputStream outputStream = new BufferedOutputStream(
               new FileOutputStream(
                     new File("D:\\home\\jee\\prepare-sharing\\spirngmv-upload-ajax\\spring-mvc-fileupload-example3\\src\\main\\webapp\\", file.getOriginalFilename())));
         outputStream.write(file.getBytes());
         outputStream.flush();
         outputStream.close();
      }else{
         return new ResponseEntity<>("Invalid file.",HttpStatus.BAD_REQUEST);
      }
      
      return new ResponseEntity<>("File Uploaded Successfully.",HttpStatus.OK);
   }
}
