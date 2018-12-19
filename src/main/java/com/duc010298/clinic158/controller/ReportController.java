package com.duc010298.clinic158.controller;

import com.duc010298.clinic158.repository.ReportFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/report")
public class ReportController {

    private ReportFormRepository reportFormRepository;

    @Autowired
    public ReportController(ReportFormRepository reportFormRepository) {
        this.reportFormRepository = reportFormRepository;
    }

    @GetMapping
    public String getReport(ModelMap modelMap) {
        modelMap.addAttribute("reportForms", reportFormRepository.findAllByOrderByOrderNumberAsc());
        return "report";
    }
}
