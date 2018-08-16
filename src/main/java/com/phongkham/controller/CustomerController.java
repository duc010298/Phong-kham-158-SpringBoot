package com.phongkham.controller;

import com.phongkham.dao.CustomerRepository;
import com.phongkham.model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/Customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String addCustomer(@RequestBody CustomerEntity customerEntity) {
        return customerRepository.addCustomer(customerEntity) ? "Lưu thành công" : "Lỗi: Lưu không thành công";
    }

    @RequestMapping(path = "/SearchContent", method = RequestMethod.GET)
    public @ResponseBody
    List<String> searchContent(@RequestParam("search") String search, @RequestParam("value") String value) {
        List<String> ret = null;
        switch (search) {
            case "inputName":
                ret = customerRepository.searchByName(value);
                break;
            case "inputAge":
                ret = customerRepository.searchByYOB(value);
                break;
            case "inputAddress":
                ret = customerRepository.searchByAddress(value);
                break;
        }
        return ret;
    }
}
