package com.duc010298.clinic158.controller;

import com.duc010298.clinic158.entity.ReportFormEntity;
import com.duc010298.clinic158.repository.ReportFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/reportForm")
public class ReportFormController {

    private ReportFormRepository reportFormRepository;

    @Autowired
    public ReportFormController(ReportFormRepository reportFormRepository) {
        this.reportFormRepository = reportFormRepository;
    }

    @GetMapping(value = "/{id}")
    public String getReportFormContent(@PathVariable("id") String id, ModelMap modelMap) {
        ReportFormEntity reportFormEntity;
        try {
            int idInt = Integer.parseInt(id);
            reportFormEntity = reportFormRepository.findById(idInt);
            if (reportFormEntity == null) throw new Exception();
        } catch (Exception e) {
            String errorCode = "404 Error: Page not found";
            String message = "Không tìm thấy trang";
            modelMap.addAttribute("errorCode", errorCode);
            modelMap.addAttribute("message", message);
            return "error";
        }
        modelMap.addAttribute("reportForms", reportFormRepository.findAllByOrderByOrderNumberAsc());
        modelMap.addAttribute("title", reportFormEntity.getReportName());
        modelMap.addAttribute("content", reportFormEntity.getContent());
        return "index";
    }
}
