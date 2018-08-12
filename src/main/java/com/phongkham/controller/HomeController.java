package com.phongkham.controller;

import com.phongkham.dao.NavsideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/Phongkham158")
public class HomeController {

    @Autowired
    private NavsideRepository navsideRepository;

    @GetMapping()
    public String initHome(ModelMap modelMap) {
        modelMap.addAttribute("navSides", navsideRepository.getNavSide());
        return "index";
    }

}
