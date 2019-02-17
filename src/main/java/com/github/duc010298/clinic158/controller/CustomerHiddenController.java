package com.github.duc010298.clinic158.controller;

import com.github.duc010298.clinic158.entity.CustomerHiddenEntity;
import com.github.duc010298.clinic158.repository.CustomerHiddenRepository;
import com.github.duc010298.clinic158.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(path = "/customerHidden")
public class CustomerHiddenController {

    private CustomerHiddenRepository customerHiddenRepository;
    private SendMail sendMail;
    private Environment environment;

    @Autowired
    public CustomerHiddenController(CustomerHiddenRepository customerHiddenRepository, SendMail sendMail, Environment environment) {
        this.customerHiddenRepository = customerHiddenRepository;
        this.sendMail = sendMail;
        this.environment = environment;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void addCustomer(@RequestBody CustomerHiddenEntity customerHiddenEntity) {
        customerHiddenRepository.save(customerHiddenEntity);

        boolean isSendMail = Boolean.parseBoolean(environment.getProperty("app.config.send-mail", "true"));
        if(isSendMail) {
            sendMail.sendMailHtml(customerHiddenEntity);
        }
    }

    @GetMapping(path = "/Search")
    public String getResultSearchHidden(@RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate, ModelMap modelMap) {
        modelMap.addAttribute("customers", customerHiddenRepository.findAllByDayVisitBetweenOrderByDayVisit(fromDate, toDate));
        return "resultHidden";
    }

    @GetMapping(path = "/report/{id}")
    public String getReport(@PathVariable("id") String idStr, ModelMap modelMap) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
            modelMap.addAttribute("errorCode", "404 Error: Page not found");
            modelMap.addAttribute("message", "Không tìm thấy trang");
            return "error";
        }
        String content = customerHiddenRepository.getReport(id);
        if (content == null) {
            modelMap.addAttribute("errorCode", "404 Error: Page not found");
            modelMap.addAttribute("message", "Không tìm thấy dữ liệu để hiển thị");
            return "error";
        }
        modelMap.addAttribute("content", content);
        return "reportDetail";
    }
}
