package com.phongkham.controller;

import com.phongkham.dao.CustomerRepository;
import com.phongkham.model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/Customer")
public class CustomerController {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(path = "/Search", method = RequestMethod.GET)
    public String searchCustomers(@RequestParam("Name") String name, @RequestParam("YOB") String YOB,
                                  @RequestParam("AddressCus") String addressCus,
                                  @RequestParam("DayVisit") @DateTimeFormat(pattern = "dd/MM/yyyy") Date dayVisit,
                                  ModelMap modelMap) {
        modelMap.addAttribute("Customers", customerRepository.searchCustomer(name, YOB, addressCus, dayVisit));
        return "result :: result";
    }

    @RequestMapping(path = "/Report", method = RequestMethod.GET)
    public @ResponseBody String getReport(@RequestParam("id") int id) {
        return customerRepository.getReport(id);
    }
}