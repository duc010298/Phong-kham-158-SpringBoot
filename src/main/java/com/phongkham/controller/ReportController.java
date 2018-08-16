package com.phongkham.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/Report")
public class ReportController {

    @GetMapping
    public String getReportForm() {
        return "report";
    }
}
