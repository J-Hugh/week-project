package com.hugh.auto.test.controller;

import com.hugh.auto.test.runtime.TestCaseActuator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RequestMapping("/")
@Controller
public class IndexController {

    @Autowired
    private TestCaseActuator testCaseActuator;

    @GetMapping("/home")
    public String index() {
       //
        // testCaseActuator.start(null);

        return "/home";
    }

    @GetMapping("/testShell")
    public String testShell() {
        //
        // testCaseActuator.start(null);

        return "/testShell";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            byte[] bytes = file.getBytes();


//          Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//          Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }
}
