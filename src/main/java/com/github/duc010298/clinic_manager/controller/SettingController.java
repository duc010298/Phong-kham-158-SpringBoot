package com.github.duc010298.clinic_manager.controller;

import com.github.duc010298.clinic_manager.entity.AppUserEntity;
import com.github.duc010298.clinic_manager.entity.ReportFormEntity;
import com.github.duc010298.clinic_manager.exception.BadRequestException;
import com.github.duc010298.clinic_manager.repository.AppUserRepository;
import com.github.duc010298.clinic_manager.repository.CustomerRepository;
import com.github.duc010298.clinic_manager.repository.ReportFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

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
        modelMap.addAttribute("reportList", reportFormRepository.findAllByOrderByOrderNumberAsc());

        return "setting-manager-form";
    }

    @DeleteMapping(value = "/manager-form/{id}")
    @ResponseBody
    public ResponseEntity deleteForm(@PathVariable("id") String id) {
        try {
            UUID reportId = UUID.fromString(id);
            Optional<ReportFormEntity> report = reportFormRepository.findById(reportId);
            if(report.isPresent()) {
                reportFormRepository.delete(report.get());
            } else {
                throw new BadRequestException();
            }
        } catch (Exception e) {
            throw new BadRequestException();
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
