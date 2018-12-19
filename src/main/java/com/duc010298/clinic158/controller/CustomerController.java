package com.duc010298.clinic158.controller;

import com.duc010298.clinic158.entity.CustomerEntity;
import com.duc010298.clinic158.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String addCustomer(@RequestBody CustomerEntity customerEntity) {
        return customerEntity.equals(customerRepository.save(customerEntity)) ? "Lưu thành công" : "Lỗi: Lưu không thành công";
    }

    @GetMapping(path = "/SearchContent")
    public @ResponseBody
    List<String> searchContent(@RequestParam("search") String search, @RequestParam("value") String value) {
        List<String> ret = null;
        switch (search) {
            case "inputName":
                ret = customerRepository.searchByName('%' + value + '%');
                break;
            case "inputAge":
                ret = new ArrayList<>();
                List<Integer> integerList = customerRepository.searchByYob('%' + value + '%');
                for(Integer integer: integerList) {
                    ret.add(integer.toString());
                }
                break;
            case "inputAddress":
                ret = customerRepository.searchByAddress('%' + value + '%');
                break;
        }
        return ret;
    }

}
