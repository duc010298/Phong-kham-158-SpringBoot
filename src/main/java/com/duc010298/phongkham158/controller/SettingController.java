package com.duc010298.phongkham158.controller;

import com.duc010298.phongkham158.dao.AppRoleRepository;
import com.duc010298.phongkham158.dao.AppUserRepository;
import com.duc010298.phongkham158.dao.FormRepository;
import com.duc010298.phongkham158.entity.AppRoleEntity;
import com.duc010298.phongkham158.entity.FormEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/manger-form/add")
    public String getFormAdd() {
        return "add";
    }

    @PostMapping(path = "/manager-form/add", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String addForm(@RequestParam("name") String name, @RequestParam("content") String content) {
        return "";
    }

    @GetMapping(path = "/manager-clinic")
    public String getFormManagerClinic() {
        return "managerclinic";
    }

    @GetMapping(path = "/manager-user")
    public String getFormManagerUser(ModelMap modelMap) {
        List<AppRoleEntity> appRoleEntities = appRoleRepository.getAllRole();
        modelMap.addAttribute("appRoleEntities", appRoleEntities);
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

}
