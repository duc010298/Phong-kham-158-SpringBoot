package com.duc010298.phongkham158.controller;

import com.duc010298.phongkham158.dao.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    private FormRepository formRepository;

    @Autowired
    public HomeController(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @GetMapping("/")
    public String initHome(ModelMap modelMap) {
        modelMap.addAttribute("forms", formRepository.getForm());
        modelMap.addAttribute("title", "Phòng khám 158");
        modelMap.addAttribute("content", "<h1>Chưa có nội dung để hiển thị</h1>");
        return "index";
    }
}
