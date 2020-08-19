package com.lowes.meetingapp.resources;

import com.lowes.meetingapp.beans.request.EmployeeRequestBean;
import com.lowes.meetingapp.beans.response.EmployeeRegisterResponseBean;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.core.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegisterEmployeeResource {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/registerEmployee")
    ResponseEntity<ResponseBean<List<EmployeeRegisterResponseBean>>> registerEmployees(@RequestBody List<EmployeeRequestBean> employeeBean){
        return new ResponseEntity<>(employeeService.registerEmployee(employeeBean),HttpStatus.OK);
    }
}
