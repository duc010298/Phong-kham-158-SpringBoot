package com.duc010298.clinic158.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping("/")
    public String initHome(ModelMap modelMap) {
//        modelMap.addAttribute("forms", formRepository.getForm());
        modelMap.addAttribute("title", "Phòng khám 158");
        modelMap.addAttribute("content", "<h1>Chưa có nội dung để hiển thị</h1>");
        return "index";
    }

}
