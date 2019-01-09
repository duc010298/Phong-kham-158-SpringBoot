package com.github.duc010298.clinic158.controller;

import com.github.duc010298.clinic158.entity.AppRoleEntity;
import com.github.duc010298.clinic158.entity.AppUserEntity;
import com.github.duc010298.clinic158.entity.ReportFormEntity;
import com.github.duc010298.clinic158.repository.AppRoleRepository;
import com.github.duc010298.clinic158.repository.AppUserRepository;
import com.github.duc010298.clinic158.repository.ReportFormRepository;
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

    @GetMapping(path = "/manager-clinic")
    public String getFormManagerClinic() {
        return "managerClinic";
    }

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
        if (appUserRepository.findByUserName(username) != null) {
            return "Tên tài khoản đã tồn tại";
        }

        AppUserEntity newUserEntity = new AppUserEntity();
        newUserEntity.setUserName(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        newUserEntity.setEncryptedPassword(password);

        Set<AppRoleEntity> appRoleEntities = processRoleArray(role);
        newUserEntity.setAppRoleEntities(appRoleEntities);

        newUserEntity = appUserRepository.save(newUserEntity);

        return newUserEntity.getUserId() == 0 ? "Thêm tài khoản không thành công" : "Thêm tài khoản thành công";
    }

    @PostMapping(path = "/manager-user/edit-user-role", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String editRoleOfUser(@RequestParam("username") String username, @RequestParam("role[]") String[] role) {
        AppUserEntity appUserEntity = appUserRepository.findByUserName(username);

        //The first account added is Root Account
        if(appUserEntity.getUserId() == 1) {
            return "Không thể sửa tài khoản root";
        }
        Set<AppRoleEntity> appRoleEntities = processRoleArray(role);
        appUserEntity.setAppRoleEntities(appRoleEntities);

        return appUserEntity.equals(appUserRepository.save(appUserEntity)) ? "Sửa đổi quyền truy cập thành công" : "Sửa quyền truy cập không thành công";
    }

    @PostMapping(path = "/manager-user/delete-user", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String deleteUser(@RequestParam("username") String username) {
        AppUserEntity appUserEntity = appUserRepository.findByUserName(username);

        //The first account added is Root Account
        if(appUserEntity.getUserId() == 1) {
            return "Không thể xóa tài khoản root";
        }

        if (appUserEntity == null) return "Xóa tài khoản không thành công";
        try {
            appUserRepository.deleteById(appUserEntity.getUserId());
            return "Xóa tài khoản thành công";
        } catch (Exception e) {
            return "Xóa tài khoản không thành công";
        }
    }

    @PostMapping(path = "/manager-user/change-user-password", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String changePassword(@RequestParam("username") String username, @RequestParam("oldPassword") String oldPassword, @RequestParam("password") String password) {
        AppUserEntity appUserEntity = appUserRepository.findByUserName(username);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(!encoder.matches(oldPassword, appUserEntity.getEncryptedPassword())) return "Mật khẩu cũ không đúng";
        
        password = encoder.encode(password);
        appUserEntity.setEncryptedPassword(password);

        return appUserEntity.equals(appUserRepository.save(appUserEntity)) ? "Đổi mật khẩu thành công" : "Đổi mật khẩu không thành công";
    }

    private Set<AppRoleEntity> processRoleArray(@RequestParam("role[]") String[] role) {
        Set<AppRoleEntity> appRoleEntities = new HashSet<>(0);
        for (String roleId : role) {
            appRoleEntities.add(appRoleRepository.getOne(Long.parseLong(roleId)));
        }
        return appRoleEntities;
    }

}
