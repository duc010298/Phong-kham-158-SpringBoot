package com.github.duc010298.clinic_manager.controller;

import com.github.duc010298.clinic_manager.entity.AppUserEntity;
import com.github.duc010298.clinic_manager.entity.ClinicEntity;
import com.github.duc010298.clinic_manager.repository.AppUserRepository;
import com.github.duc010298.clinic_manager.repository.ReportFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    private ReportFormRepository reportFormRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public HomeController(ReportFormRepository reportFormRepository, AppUserRepository appUserRepository) {
        this.reportFormRepository = reportFormRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping
    public String initHome(ModelMap modelMap, Principal principal) {
        AppUserEntity currentUser = appUserRepository.findByUserName(principal.getName());
        modelMap.addAttribute("fullName", currentUser.getFullName());

        ClinicEntity currentClinic = currentUser.getClinicByClinicId();
        if (currentClinic == null) {
            modelMap.addAttribute("reportForms", reportFormRepository.findAllByOrderByOrderNumberAsc());
        } else {
            modelMap.addAttribute("reportForms", reportFormRepository.findAllByClinicByClinicIdOrderByOrderNumberAsc(currentClinic));
        }
        modelMap.addAttribute("activeReportId", null);
        modelMap.addAttribute("content", null);
        return "index";
    }
}
