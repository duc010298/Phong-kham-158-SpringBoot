package com.duc010298.phongkham158.controller;

import com.duc010298.phongkham158.dao.CustomerRepository;
import com.duc010298.phongkham158.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/customer")
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
    public String searchCustomers(@RequestParam("NameS") String nameS, @RequestParam("YOB") String YOB,
                                  @RequestParam("AddressCusS") String addressCusS,
                                  @RequestParam("DayVisit") @DateTimeFormat(pattern = "dd/MM/yyyy") Date dayVisit,
                                  ModelMap modelMap) {
        modelMap.addAttribute("Customers", customerRepository.searchCustomer(nameS, YOB, addressCusS, dayVisit));
        return "result :: result";
    }

    @RequestMapping(path = "/Report/{id}")
    public String getReport(@PathVariable("id") String id, ModelMap modelMap) {
        int idInt;
        try {
            idInt = Integer.parseInt(id);
        } catch(Exception e) {
            modelMap.addAttribute("errorCode", "404 Error: Page not found");
            modelMap.addAttribute("message", "Không tìm thấy trang");
            return "error";
        }
        String content = customerRepository.getReport(idInt);
        if(content == null) {
            modelMap.addAttribute("errorCode", "404 Error: Page not found");
            modelMap.addAttribute("message", "Không tìm thấy dữ liệu để hiển thị");
            return "error";
        }
        modelMap.addAttribute("content", content);
        return "reportdetail";
    }
}
