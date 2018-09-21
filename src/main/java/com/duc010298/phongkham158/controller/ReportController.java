package com.duc010298.phongkham158.controller;

import com.duc010298.phongkham158.dao.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/report")
public class ReportController {

    private FormRepository formRepository;

    @Autowired
    public ReportController(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @GetMapping
    public String getReport(ModelMap modelMap) {
        modelMap.addAttribute("forms", formRepository.getForm());
        return "report";
    }
}
