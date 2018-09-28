package com.duc010298.phongkham158.controller;

import com.duc010298.phongkham158.dao.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/form")
public class FormController {

    private FormRepository formRepository;

    @Autowired
    public FormController(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @GetMapping(value = "/{id}")
    public String getFormContent(@PathVariable("id") String id, ModelMap modelMap) {
        String content = null;
        String title = null;
        try {
            Integer.parseInt(id);
            content = formRepository.getFormContent(id);
            title = formRepository.getFormName(id);
        } catch(Exception e) {
            String errorCode = "404 Error: Page not found";
            String message = "Không tìm thấy trang";
            modelMap.addAttribute("errorCode", errorCode);
            modelMap.addAttribute("message", message);
            return "error";
        }
        modelMap.addAttribute("forms", formRepository.getForm());
        modelMap.addAttribute("title", title);
        modelMap.addAttribute("content", content);
        return "index";
    }
}
