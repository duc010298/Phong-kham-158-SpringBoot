package com.duc010298.clinic158.controller;

import com.duc010298.clinic158.entity.CustomerEntity;
import com.duc010298.clinic158.repository.CustomerRepository;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String addCustomer(@RequestBody CustomerEntity customerEntity) {
        customerEntity = customerRepository.save(customerEntity);
        return customerEntity.getId() != 0 ? "Lưu thành công" : "Lỗi: Lưu không thành công";
    }

    @GetMapping(path = "/SearchContent")
    public @ResponseBody
    List<String> searchContent(@RequestParam("search") String search, @RequestParam("value") String value) {
        List<String> ret = null;
        switch (search) {
            case "inputName":
                ret = customerRepository.searchTop10ByName(value);
                break;
            case "inputAge":
                ret = customerRepository.searchTop10ByYob(value);
                break;
            case "inputAddress":
                ret = customerRepository.searchTop10ByAddress(value);
                break;
        }
        return ret;
    }

    @GetMapping(path = "/Search")
    public String searchCustomers(@RequestParam("nameSearch") String nameSearch, @RequestParam("yob") String yobStr,
                                  @RequestParam("addressSearch") String addressSearch,
                                  @RequestParam("dayVisit") @DateTimeFormat(pattern = "dd/MM/yyyy") Date dayVisit,
                                  ModelMap modelMap) {
        int yob;
        try {
            yob = Integer.parseInt(yobStr);
        } catch (NumberFormatException e) {
            yob = 0;
        }
        List<CustomerEntity> customerEntities;
        if (dayVisit == null && yob != 0) {
            customerEntities = customerRepository.searchTop100CustomerWithoutDayVisit(nameSearch, yob, addressSearch);
        } else if (dayVisit == null && yob == 0) {
            customerEntities = customerRepository.searchTop100CustomerWithoutYobAndDayVisit(nameSearch, addressSearch);
            System.out.println("abc:" + customerEntities.get(0).toString());
        } else if (dayVisit != null && yob != 0) {
            customerEntities = customerRepository.searchTop100Customer(nameSearch, yob, addressSearch, dayVisit);
        } else {
            customerEntities = customerRepository.searchTop100CustomerWithoutYob(nameSearch, addressSearch, dayVisit);
        }
        modelMap.addAttribute("customers", customerEntities);
        return "result";
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
        String content = customerRepository.getReport(id);
        if (content == null) {
            modelMap.addAttribute("errorCode", "404 Error: Page not found");
            modelMap.addAttribute("message", "Không tìm thấy dữ liệu để hiển thị");
            return "error";
        }
        modelMap.addAttribute("content", content);
        return "reportDetail";
    }

}
