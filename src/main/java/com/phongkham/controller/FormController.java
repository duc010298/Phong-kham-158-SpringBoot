package com.phongkham.controller;

import com.phongkham.dao.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping(path = "/UltraSoundResult")
public class FormController {

    @Autowired
    private FormRepository formRepository;

    @GetMapping()
    public String getFormContent(@RequestParam("id") String id, ModelMap modelMap) {
        modelMap.addAttribute("formContents", formRepository.getFormContent(id));
        modelMap.addAttribute("date", new Date());
        return "form" + formRepository.getFormId(id) + " :: result";
    }

}
