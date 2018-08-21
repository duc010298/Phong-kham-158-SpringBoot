package com.phongkham.controller;

import com.phongkham.dao.CustomerHiddenRepository;
import com.phongkham.model.CustomerEntity;
import com.phongkham.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(path = "/CustomerHidden")
public class CustomerHiddenController {

    private CustomerHiddenRepository customerHiddenRepository;
    private SendMail sendMail;

    @Autowired
    public CustomerHiddenController(CustomerHiddenRepository customerHiddenRepository, SendMail sendMail) {
        this.customerHiddenRepository = customerHiddenRepository;
        this.sendMail = sendMail;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(path = "/Search", method = RequestMethod.GET)
    public String getResultSearchHidden(@RequestParam("dayVisit") Date dayVisit, ModelMap modelMap) {
        modelMap.addAttribute("Customers", customerHiddenRepository.searchCustomer(dayVisit));
        return "resultHidden";
    }

    @RequestMapping(path = "/Report", method = RequestMethod.GET)
    public @ResponseBody String getReport(@RequestParam("id") int id) {
        return customerHiddenRepository.getReport(id);
    }

}
