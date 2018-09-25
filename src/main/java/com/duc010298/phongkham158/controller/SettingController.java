package com.duc010298.phongkham158.controller;

import com.duc010298.phongkham158.dao.FormRepository;
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

    @Autowired
    public SettingController(FormRepository formRepository) {
        this.formRepository = formRepository;
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

    @GetMapping
    public String getFormAdd() {
        return "add";
    }

    @PostMapping(path = "/manager-form/add", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String addForm(@RequestParam("name") String name, @RequestParam("content") String content) {
        return "";
    }

}
