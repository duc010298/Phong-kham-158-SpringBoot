package com.phongkham.controller;

import com.phongkham.dao.FormRepository;
import com.phongkham.dao.NavsideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(path = "/Setting")
public class SettingController {

    private NavsideRepository navsideRepository;
    private FormRepository formRepository;

    @Autowired
    public SettingController(NavsideRepository navsideRepository, FormRepository formRepository) {
        this.navsideRepository = navsideRepository;
        this.formRepository = formRepository;
    }

    @GetMapping
    public String settingForm(ModelMap modelMap) {
        modelMap.addAttribute("navSides", navsideRepository.getNavSide());
        return "setting";
    }

    @RequestMapping(path = "/Delete", method = RequestMethod.POST)
    public @ResponseBody String deleteForm(@RequestParam("id") String id) {
        boolean b1 = formRepository.deleteForm(id);
        boolean b2 = navsideRepository.deleteNavSide(id);
        return b1 && b2 ? "Xóa thành công" : "Xóa không thành công";
    }

    @RequestMapping(path = "/Edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") String id, ModelMap modelMap) {
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("formContents", formRepository.getFormContent(id));
        modelMap.addAttribute("date", new Date());
        return "edit";
    }

    @RequestMapping(path = "/Add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("date", new Date());
        return "add";
    }

}
