package com.lowes.meetingapp.core.service.interfaces;

import com.lowes.meetingapp.beans.request.EmployeeRequestBean;
import com.lowes.meetingapp.beans.response.EmployeeRegisterResponseBean;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.core.exception.BusinessServiceException;
import com.lowes.meetingapp.core.dao.beans.EmployeeDO;

import java.util.List;

public interface IEmployeeService {

    public ResponseBean<List<EmployeeRegisterResponseBean>> registerEmployee(List<EmployeeRequestBean> employeeRequestBean) throws BusinessServiceException;
    public EmployeeDO getEmployee(Long emloyeeId) throws BusinessServiceException;
    public List<EmployeeDO> getListOfEmployees(Long officeId) throws BusinessServiceException;

}
