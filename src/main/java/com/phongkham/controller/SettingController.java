package com.phongkham.controller;

import com.phongkham.dao.FormRepository;
import com.phongkham.dao.NavsideRepository;
import com.phongkham.model.FormEntity;
import com.phongkham.utils.ParseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/Setting")
public class SettingController {

    private NavsideRepository navsideRepository;
    private FormRepository formRepository;
    private ParseData parseData;

    @Autowired
    public SettingController(NavsideRepository navsideRepository, FormRepository formRepository,ParseData parseData) {
        this.navsideRepository = navsideRepository;
        this.formRepository = formRepository;
        this.parseData = parseData;
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

    @RequestMapping(value = "/Edit", method = RequestMethod.POST)
    public @ResponseBody String editForm(@RequestParam("id") String id, @RequestParam("data") String data) {
        List<FormEntity> formEntities = parseData.parseJson(data);
        if(!formRepository.deleteForm(id)) {
            return "Lưu không thành công";
        }
        return formRepository.updateForm(id, formEntities) ? "Lưu thành công" : "Lưu không thành công";
    }

    @RequestMapping(path = "/Add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("date", new Date());
        return "add";
    }

    @RequestMapping(path = "/Add", method = RequestMethod.POST)
    public @ResponseBody String addForm(@RequestParam("name") String name, @RequestParam("data") String data) {
        List<FormEntity> formEntities = parseData.parseJson(data);
        if(!navsideRepository.addNavSide(name)) {
            return "Thêm không thành công";
        }
        String newId = formRepository.getMaxUltraSouldResultId();
        return formRepository.updateForm(newId, formEntities) ? "Lưu thành công" : "Lưu không thành công";
    }

}
