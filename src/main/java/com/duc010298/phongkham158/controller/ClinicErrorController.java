package com.duc010298.phongkham158.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ClinicErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, ModelMap modelMap) {
        String errorCode = "Unknown error";
        String message = "Lỗi không xác định";

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                errorCode = "404 Error: Page not found";
                message = "Không tìm thấy trang";
            } else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                errorCode = "403 Error: Access denied";
                message = "Bạn không thể truy cập trang này";
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorCode = "500 Error: Internal Server Error";
                message = "Server bất ngờ bị lỗi";
            } else {
                errorCode = statusCode.toString();
            }
        }

        modelMap.addAttribute("errorCode", errorCode);
        modelMap.addAttribute("message", message);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
