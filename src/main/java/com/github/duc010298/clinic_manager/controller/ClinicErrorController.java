package com.github.duc010298.clinic_manager.controller;

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
        String errorCode = "Error";
        String message_header = "Unknown error";
        String message_content = "Lỗi không xác định";

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null) {
            int statusCode = Integer.parseInt(status.toString());
            errorCode = Integer.toString(statusCode);
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                message_header = "Page not found";
                message_content = "Không tìm thấy trang";
            } else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                message_header = "Access denied";
                message_content = "Bạn không thể truy cập trang này";
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                message_header = "Internal Server Error";
                message_content = "Server bất ngờ bị lỗi";
            } else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                message_header = "Method not allowed";
                message_content = "Truy cập không được phép";
            }
        }

        modelMap.addAttribute("errorCode", errorCode);
        modelMap.addAttribute("message_header", message_header);
        modelMap.addAttribute("message_content", message_content);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
