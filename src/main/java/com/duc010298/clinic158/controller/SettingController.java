package com.duc010298.clinic158.controller;

import com.duc010298.clinic158.entity.AppRoleEntity;
import com.duc010298.clinic158.entity.AppUserEntity;
import com.duc010298.clinic158.entity.ReportFormEntity;
import com.duc010298.clinic158.repository.AppRoleRepository;
import com.duc010298.clinic158.repository.AppUserRepository;
import com.duc010298.clinic158.repository.ReportFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path = "/setting")
public class SettingController {

    private ReportFormRepository reportFormRepository;
    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public SettingController(ReportFormRepository reportFormRepository, AppRoleRepository appRoleRepository, AppUserRepository appUserRepository) {
        this.reportFormRepository = reportFormRepository;
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
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

        reportFormEntity = reportFormRepository.save(reportFormEntity);

        return reportFormEntity.getId() != 0 ? "Lưu thành công" : "Lưu không thành công";
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
        ReportFormEntity reportFormEntity = reportFormRepository.findById(Integer.parseInt(id));
        reportFormEntity.setContent(content);
        return reportFormEntity.equals(reportFormRepository.save(reportFormEntity)) ? "Sửa thành công" : "Sửa không thành công";
    }

//    @GetMapping(path = "/manager-clinic")
//    public String getFormManagerClinic() {
//        return "managerclinic";
//    }

    @GetMapping(path = "/manager-user")
    public String getFormManagerUser(ModelMap modelMap) {
        List<AppRoleEntity> appRoleEntities = appRoleRepository.findAll();
        modelMap.addAttribute("appRoleEntities", appRoleEntities);

        List<AppUserEntity> appUserEntities = appUserRepository.findAll();
        Map<String, List<String>> listMap = new HashMap<>();
        for (AppUserEntity appUserEntity : appUserEntities) {
            listMap.put(appUserEntity.getUserName(), appRoleRepository.getRoleNames(appUserEntity.getUserId()));
        }
        modelMap.addAttribute("listMap", listMap);
        return "managerUser";
    }

    @PostMapping(path = "/manager-user/add-user", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String addUser(@RequestParam("username") String username, @RequestParam("password") String password,
                   @RequestParam("role[]") String[] role) {
        AppUserEntity newUserEntity = new AppUserEntity();
        newUserEntity.setUserName(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        newUserEntity.setEncryptedPassword(password);

        Set<AppRoleEntity> appRoleEntities = new HashSet<>(0);
        for(String roleId : role) {
            appRoleEntities.add(appRoleRepository.getOne(Long.parseLong(roleId)));
        }
        newUserEntity.setAppRoleEntities(appRoleEntities);

        newUserEntity = appUserRepository.save(newUserEntity);

        return newUserEntity.getUserId() == 0 ? "Thêm tài khoản không thành công" : "Thêm tài khoản thành công";
    }
}
