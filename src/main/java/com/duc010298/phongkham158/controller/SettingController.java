package com.duc010298.phongkham158.controller;

import com.duc010298.phongkham158.dao.AppRoleRepository;
import com.duc010298.phongkham158.dao.AppUserRepository;
import com.duc010298.phongkham158.dao.FormRepository;
import com.duc010298.phongkham158.entity.AppRoleEntity;
import com.duc010298.phongkham158.entity.AppUserEntity;
import com.duc010298.phongkham158.entity.FormEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/setting")
public class SettingController {

    private FormRepository formRepository;
    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public SettingController(FormRepository formRepository, AppRoleRepository appRoleRepository,
                             AppUserRepository appUserRepository) {
        this.formRepository = formRepository;
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/manager-form")
    public String managerForm(ModelMap modelMap) {
        List<FormEntity> formEntities = formRepository.getForm();
        modelMap.addAttribute("forms", formEntities);
        return "managerform";
    }

    @PostMapping(path = "/manager-form/delete", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String deleteForm(@RequestParam("id") String id) {
        return formRepository.deleteForm(id) ? "Xóa thành công" : "Xóa không thành công";
    }

    @GetMapping(path = "/manager-form/add")
    public String getFormAdd() {
        return "add";
    }

    @PostMapping(path = "/manager-form/add", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String addForm(@RequestParam("name") String name, @RequestParam("content") String content) {
        return formRepository.addForm(name, content) ? "Lưu thành công" : "Lưu không thành công";
    }

    @GetMapping(path = "/manager-form/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap modelMap) {
        String content;
        try {
            content = formRepository.getFormContent(id);
        } catch (Exception ex) {
            String errorCode = "404 Error: Page not found";
            String message = "Không tìm thấy trang";
            modelMap.addAttribute("errorCode", errorCode);
            modelMap.addAttribute("message", message);
            return "error";
        }
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("content", content);
        return "edit";
    }

    @PostMapping(path = "/manager-form/edit", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String editForm(@RequestParam("id") String id, @RequestParam("content") String content) {
        return formRepository.updateForm(id, content) ? "Sửa thành công" : "Sửa không thành công";
    }

    @GetMapping(path = "/manager-clinic")
    public String getFormManagerClinic() {
        return "managerclinic";
    }

    @GetMapping(path = "/manager-user")
    public String getFormManagerUser(ModelMap modelMap) {
        List<AppRoleEntity> appRoleEntities = appRoleRepository.getAllRole();
        modelMap.addAttribute("appRoleEntities", appRoleEntities);

        List<AppUserEntity> appUserEntities = appUserRepository.getAllUser();
        Map<String, List<String>> listMap = new HashMap<>();
        for(AppUserEntity appUserEntity: appUserEntities) {
            listMap.put(appUserEntity.getUserName(), appRoleRepository.getRoleNames(appUserEntity.getUserId()));
        }
        modelMap.addAttribute("listMap", listMap);
        return "manageruser";
    }

    @PostMapping(path = "/manager-user/add-user", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String addUser(@RequestParam("username") String username, @RequestParam("password") String password,
                   @RequestParam("role[]") String[] role) {
        if(!appUserRepository.addUser(username, password)) {
            return "Thêm tài khoản không thành công";
        }
        String userId = appUserRepository.findUserAccount(username).getUserId().toString();
        for(String roleId : role) {
            if(!appRoleRepository.addRoleToUser(roleId, userId)) {
                return "Phân quyền cho tài khoản không thành công";
            }
        }
        return "Thêm tài khoản thành công";
    }

    @PostMapping(path = "/manager-user/edit-user", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String editUser(@RequestParam("username") String username, @RequestParam("role[]") String[] role) {
        String userId = appUserRepository.findUserAccount(username).getUserId().toString();
        if(!appRoleRepository.deleteRoleofUser(userId)) {
            return "Sửa quyền truy cập không thành công";
        }
        for(String roleId : role) {
            if(!appRoleRepository.addRoleToUser(roleId, userId)) {
                return "Phân quyền cho tài khoản không thành công";
            }
        }
        return "Sửa đổi quyền truy cập thành công";
    }

    @PostMapping(path = "/manager-user/delete-user", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String deleteUser(@RequestParam("username") String username) {
        String userId = appUserRepository.findUserAccount(username).getUserId().toString();
        if(!appRoleRepository.deleteRoleofUser(userId)) {
            return "Xóa quyền truy cập không thành công";
        }
        return appUserRepository.deleteUser(userId) ? "Xóa tài khoản thành công" : "Xóa tài khoản không thành công";
    }

}
