package com.github.duc010298.clinic158.controller;

import com.github.duc010298.clinic158.repository.ReportFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    private ReportFormRepository reportFormRepository;

    @Autowired
    public HomeController(ReportFormRepository reportFormRepository) {
        this.reportFormRepository = reportFormRepository;
    }

    @GetMapping("/")
    public String initHome(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("username", principal.getName());
        modelMap.addAttribute("reportForms", reportFormRepository.findAllByOrderByOrderNumberAsc());
        modelMap.addAttribute("title", "Phòng khám 158");
        modelMap.addAttribute("content", "<h1>Chưa có nội dung để hiển thị</h1>");
        return "index";
    }

}
