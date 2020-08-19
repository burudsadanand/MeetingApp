package com.lowes.meetingapp.core.dao.interfaces;

import com.lowes.meetingapp.core.exception.DAOException;
import com.lowes.meetingapp.core.dao.beans.EmployeeDO;

public interface IEmployeeDao {

    public Boolean register(EmployeeDO employeeModel) throws DAOException;

    public Boolean update(String employeeId,EmployeeDO employeeModel) throws DAOException;
}
