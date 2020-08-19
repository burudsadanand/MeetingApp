package com.lowes.meetingapp.core.dao.interfaces;

import com.lowes.meetingapp.core.dao.beans.EmployeeCalendarDO;
import com.lowes.meetingapp.core.exception.DAOException;

import java.util.List;

public interface IEmployeeCalendorDao {


   public EmployeeCalendarDO createEmployeeCalendor(EmployeeCalendarDO employeeCalendarDO);
   public EmployeeCalendarDO updateEmployeeCalendor(String emailId, EmployeeCalendarDO employeeCalendarDO);
   public EmployeeCalendarDO fetchEmployeCalendor(String emaild) throws DAOException;
   public List<EmployeeCalendarDO> getEmployeeCalendor(List<String> emailIds) throws DAOException;


}
