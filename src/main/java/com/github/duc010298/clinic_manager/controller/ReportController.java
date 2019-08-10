package com.github.duc010298.clinic_manager.controller;

import com.github.duc010298.clinic_manager.entity.AppUserEntity;
import com.github.duc010298.clinic_manager.entity.ReportFormEntity;
import com.github.duc010298.clinic_manager.exception.BadRequestException;
import com.github.duc010298.clinic_manager.exception.NotFoundException;
import com.github.duc010298.clinic_manager.repository.AppUserRepository;
import com.github.duc010298.clinic_manager.repository.ReportFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(path = "/report")
public class ReportController {
    private ReportFormRepository reportFormRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public ReportController(ReportFormRepository reportFormRepository, AppUserRepository appUserRepository) {
        this.reportFormRepository = reportFormRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping(value = "/{id}")
    public String getReportFormContent(@PathVariable("id") String id, ModelMap modelMap, Principal principal) {
        AppUserEntity currentUser = appUserRepository.findByUserName(principal.getName());
        modelMap.addAttribute("fullName", currentUser.getFullName());

        try {
            Optional<ReportFormEntity> optionalCurrentReport = reportFormRepository.findById(UUID.fromString(id));
            if (!optionalCurrentReport.isPresent()) {
                throw new NotFoundException();
            }
            ReportFormEntity currentReport = optionalCurrentReport.get();
            modelMap.addAttribute("activeReportId", currentReport.getId());
            modelMap.addAttribute("title", currentReport.getReportName());
            modelMap.addAttribute("content", currentReport.getContent());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException();
        }

        return "index";
    }
}
