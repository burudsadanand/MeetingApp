package com.lowes.meetingapp.core.dao.impl;

import com.lowes.meetingapp.core.dao.beans.EmployeeCalendarDO;
import com.lowes.meetingapp.core.dao.interfaces.IEmployeeCalendorDao;
import com.lowes.meetingapp.core.exception.DAOException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EmployeeCalendorDao implements IEmployeeCalendorDao {

    @Inject
    private Map<String,EmployeeCalendarDO> employeeCalendarDOMap;


    @Override
    public EmployeeCalendarDO createEmployeeCalendor(EmployeeCalendarDO employeeCalendarDO) {
        return employeeCalendarDOMap.put(employeeCalendarDO.getEmployeeEmailId(),employeeCalendarDO);
    }

    @Override
    public EmployeeCalendarDO updateEmployeeCalendor(String emailId, EmployeeCalendarDO employeeCalendarDO) {
        return employeeCalendarDOMap.put(emailId,employeeCalendarDO);
    }

    @Override
    public EmployeeCalendarDO fetchEmployeCalendor(String employeEmailId) throws DAOException {

        return employeeCalendarDOMap.get(employeEmailId);
    }

    @Override
    public List<EmployeeCalendarDO> getEmployeeCalendor(List<String> emailIds) throws DAOException {
           List<EmployeeCalendarDO> employeeCalendorDOS=new ArrayList<>();
           emailIds.stream().forEach(emailId->{
               employeeCalendorDOS.add(employeeCalendarDOMap.get(emailId));
           });

        return employeeCalendorDOS;
    }
}
