package com.duc010298.clinic158.controller;

import com.duc010298.clinic158.entity.CustomerHiddenEntity;
import com.duc010298.clinic158.repository.CustomerHiddenRepository;
import com.duc010298.clinic158.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(path = "/customerHidden")
public class CustomerHiddenController {

    private CustomerHiddenRepository customerHiddenRepository;
    private SendMail sendMail;

    @Autowired
    public CustomerHiddenController(CustomerHiddenRepository customerHiddenRepository, SendMail sendMail) {
        this.customerHiddenRepository = customerHiddenRepository;
        this.sendMail = sendMail;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void addCustomer(@RequestBody CustomerHiddenEntity customerHiddenEntity) {
        customerHiddenRepository.save(customerHiddenEntity);

        // TODO move setting to config file
        String From = "phongkham158@gmail.com";
        String To = "duc010298@gmail.com";
        String Subject = "Vào ngày ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(customerHiddenEntity.getDayVisit());
        Subject += date;
        Subject += " đã có 1 ca đã được in nhưng chưa được lưu";
        sendMail.sendMailHtml(customerHiddenEntity, From, To, Subject);
    }

    @GetMapping(path = "/Search")
    public String getResultSearchHidden(@RequestParam("dayVisit") Date dayVisit, ModelMap modelMap) {
        modelMap.addAttribute("customers", customerHiddenRepository.findAllByDayVisitOrderByDayVisit(dayVisit));
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
