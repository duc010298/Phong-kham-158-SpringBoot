package com.github.duc010298.clinic_manager.controller;

import com.github.duc010298.clinic_manager.entity.AppUserEntity;
import com.github.duc010298.clinic_manager.entity.ReportFormEntity;
import com.github.duc010298.clinic_manager.repository.AppUserRepository;
import com.github.duc010298.clinic_manager.repository.CustomerRepository;
import com.github.duc010298.clinic_manager.repository.ReportFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(path = "/setting")
public class SettingController {

    private AppUserRepository appUserRepository;
    private CustomerRepository customerRepository;
    private ReportFormRepository reportFormRepository;

    @Autowired
    public SettingController(AppUserRepository appUserRepository, CustomerRepository customerRepository, ReportFormRepository reportFormRepository) {
        this.appUserRepository = appUserRepository;
        this.customerRepository = customerRepository;
        this.reportFormRepository = reportFormRepository;
    }

    @GetMapping
    public String settingHome(ModelMap modelMap, Principal principal) {
        AppUserEntity currentUser = appUserRepository.findByUserName(principal.getName());
        modelMap.addAttribute("fullName", currentUser.getFullName());
        return "setting-home";
    }

    @GetMapping("/manager-form")
    public String settingManagerForm(ModelMap modelMap, Principal principal) {
        AppUserEntity currentUser = appUserRepository.findByUserName(principal.getName());
        modelMap.addAttribute("fullName", currentUser.getFullName());

        return "setting-manager-form";
    }

    @GetMapping("/manager-form/list")
    public @ResponseBody
    List<ReportFormEntity> settingManagerForm() {
        return reportFormRepository.findAllByOrderByOrderNumberAsc();
    }
}
