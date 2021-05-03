package com.kyo.basic.process.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
    @GetMapping("/sample")
    public String sample(Model model) {
        model.addAttribute("name", "kyo");
        return "sample";
    }
}
