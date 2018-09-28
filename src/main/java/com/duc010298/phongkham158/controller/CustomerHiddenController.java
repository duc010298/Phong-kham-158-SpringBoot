package com.duc010298.phongkham158.controller;

import com.duc010298.phongkham158.dao.CustomerHiddenRepository;
import com.duc010298.phongkham158.entity.CustomerEntity;
import com.duc010298.phongkham158.utils.SendMail;
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
    public @ResponseBody void addCustomer(@RequestBody CustomerEntity customerEntity) {
        customerHiddenRepository.addCustomer(customerEntity);

        String From = "phongkham158@gmail.com";
        String To = "duc010298@gmail.com";
        String Subject = "Vào ngày ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(customerEntity.getDayVisit());
        Subject += date;
        Subject += " đã có 1 ca đã được in nhưng chưa được lưu";
        sendMail.sendMailHtml(customerEntity, From, To, Subject);
    }

    @GetMapping(path = "/Search")
    public String getResultSearchHidden(@RequestParam("dayVisit") Date dayVisit, ModelMap modelMap) {
        modelMap.addAttribute("Customers", customerHiddenRepository.searchCustomer(dayVisit));
        return "result :: resulthidden";
    }

    @GetMapping(path = "/Report/{id}")
    public String getReport(@PathVariable("id") String id, ModelMap modelMap) {
        int idInt;
        try {
            idInt = Integer.parseInt(id);
        } catch(Exception e) {
            modelMap.addAttribute("errorCode", "404 Error: Page not found");
            modelMap.addAttribute("message", "Không tìm thấy trang");
            return "error";
        }
        String content = customerHiddenRepository.getReport(idInt);
        if(content == null) {
            modelMap.addAttribute("errorCode", "404 Error: Page not found");
            modelMap.addAttribute("message", "Không tìm thấy dữ liệu để hiển thị");
            return "error";
        }
        modelMap.addAttribute("content", content);
        return "reportdetail";
    }

}

