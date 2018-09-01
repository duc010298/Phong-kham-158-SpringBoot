package com.phongkham.controller;

import com.phongkham.dao.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/Setting")
public class SettingController {

    private FormRepository formRepository;

    @Autowired
    public SettingController(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @GetMapping
    public String loadForm(ModelMap modelMap) {
        modelMap.addAttribute("forms", formRepository.getForm());
        return "setting";
    }

    @RequestMapping(path = "/Add")
    public String add() {
        return "add";
    }

    @RequestMapping(path = "/Add", method = RequestMethod.POST)
    public @ResponseBody
    String addForm(@RequestParam("name") String name,
               @RequestParam("content") String content) {
        System.out.println(content);
        return formRepository.addForm(name, content) ? "Lưu thành công" : "Lưu không thành công";
    }

    @RequestMapping(path = "/Delete", method = RequestMethod.POST)
    public @ResponseBody String deleteForm(@RequestParam("id") String id) {
        return formRepository.deleteForm(id) ? "Xóa thành công" : "Xóa không thành công";
    }

    @RequestMapping(path = "/Edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") String id, ModelMap modelMap) {
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("content", formRepository.getFormContent(id));
        return "edit";
    }

    @RequestMapping(path = "/Edit", method = RequestMethod.POST)
    public @ResponseBody String editForm(@RequestParam("id") String id,
                           @RequestParam("content") String content) {
        System.out.println(content);
        return formRepository.updateForm(id, content) ? "Sửa thành công" : "Sửa không thành công";
    }
}
