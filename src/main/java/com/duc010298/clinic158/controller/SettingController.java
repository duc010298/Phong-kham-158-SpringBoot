package com.duc010298.clinic158.controller;

import com.duc010298.clinic158.entity.ReportFormEntity;
import com.duc010298.clinic158.repository.ReportFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/setting")
public class SettingController {

    private ReportFormRepository reportFormRepository;

    @Autowired
    public SettingController(ReportFormRepository reportFormRepository) {
        this.reportFormRepository = reportFormRepository;
    }

    @GetMapping("/manager-form")
    public String managerForm(ModelMap modelMap) {
        List<ReportFormEntity> reportFormEntities = reportFormRepository.findAllByOrderByOrderNumberAsc();
        modelMap.addAttribute("reportForms", reportFormEntities);
        return "managerForm";
    }

    @PostMapping(path = "/manager-form/delete", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String deleteForm(@RequestParam("id") String id) {
        try {
            reportFormRepository.deleteById(Integer.parseInt(id));
            return "Xóa thành công";
        } catch (Exception e) {
            return "Xóa không thành công";
        }
    }

    @GetMapping(path = "/manager-form/add")
    public String getFormAdd() {
        return "add";
    }

    @PostMapping(path = "/manager-form/add", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String addForm(@RequestParam("name") String name, @RequestParam("content") String content) {
        Integer maxOrderNumber = reportFormRepository.getMaxOrderNumber();
        maxOrderNumber = maxOrderNumber == null ? 0 : maxOrderNumber++;
        ReportFormEntity reportFormEntity = new ReportFormEntity();
        reportFormEntity.setOrderNumber(maxOrderNumber);
        reportFormEntity.setContent(content);
        reportFormEntity.setReportName(name);
        try {
            reportFormRepository.save(reportFormEntity);
            return "Lưu thành công";
        } catch (Exception e) {
            return "Lưu không thành công";
        }
    }

    @GetMapping(path = "/manager-form/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap modelMap) {
        String content;
        try {
            content = reportFormRepository.findById(Integer.parseInt(id)).getContent();
        } catch (Exception ex) {
            String errorCode = "404 Error: Page not found";
            String message = "Không tìm thấy trang ";
            modelMap.addAttribute("errorCode", errorCode);
            modelMap.addAttribute("message", message);
            return "error";
        }
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("content", content);
        return "edit";
    }

    @PostMapping(path = "/manager-form/edit", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String editForm(@RequestParam("id") String id, @RequestParam("content") String content) {
        try {
            ReportFormEntity reportFormEntity = reportFormRepository.findById(Integer.parseInt(id));
            reportFormEntity.setContent(content);
            reportFormRepository.save(reportFormEntity);
            return "Sửa thành công";
        } catch (Exception e) {
            return "Sửa không thành công";
        }
    }
}
