package com.phongkham.controller;

import com.phongkham.dao.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/")
//@RequestMapping(path = "/Phongkham158")
public class HomeController {

    private FormRepository formRepository;

    @Autowired
    public HomeController(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @GetMapping()
    public String initHome(ModelMap modelMap) {
        modelMap.addAttribute("forms", formRepository.getForm());
        return "index";
    }

    @RequestMapping(path = "/Form", method = RequestMethod.GET)
    public @ResponseBody
    String getFormContent(@RequestParam("id") String id) {
        return formRepository.getFormContent(id);
    }
}
