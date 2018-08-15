package com.phongkham.controller;

import com.phongkham.dao.CustomerRepository;
import com.phongkham.model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/Customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String addCustomer(@RequestBody CustomerEntity customerEntity) {
        String message;
        if(customerRepository.addCustomer(customerEntity)) {
            message = "Lưu thành công";
        } else {
            message = "Lỗi: Lưu không thành công";
        }
        return message;
    }
}
