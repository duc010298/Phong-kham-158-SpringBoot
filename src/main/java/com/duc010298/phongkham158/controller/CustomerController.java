package com.duc010298.phongkham158.controller;

import com.duc010298.phongkham158.dao.CustomerRepository;
import com.duc010298.phongkham158.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/Customer")
public class CustomerController {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String addCustomer(@RequestBody CustomerEntity customerEntity) {
        return customerRepository.addCustomer(customerEntity) ? "Lưu thành công" : "Lỗi: Lưu không thành công";
    }
}
