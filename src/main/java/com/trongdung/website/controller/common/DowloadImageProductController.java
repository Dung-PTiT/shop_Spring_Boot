package com.trongdung.website.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;

@Controller
public class DowloadImageProductController {
    @GetMapping("/image/product")
    public void download(@RequestParam(name="fileName") String fileName,
                         HttpServletResponse response){
        try {
            final String UPLOAD_FOLDER = "D:\\product\\img";
            File file = new File(UPLOAD_FOLDER + File.separator + fileName);
            if(file.exists()){
                Files.copy(file.toPath(),response.getOutputStream());
            }
        }catch (Exception e){
            System.out.println("Loi: " +e);
        }
    }
}
