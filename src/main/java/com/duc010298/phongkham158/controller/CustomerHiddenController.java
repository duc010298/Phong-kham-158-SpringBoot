package com.duc010298.phongkham158.controller;

import com.duc010298.phongkham158.dao.CustomerHiddenRepository;
import com.duc010298.phongkham158.entity.CustomerEntity;
import com.duc010298.phongkham158.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

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

}

