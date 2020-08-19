package com.lowes.meetingapp.core.dao.impl;

import com.lowes.meetingapp.core.constants.ErrorEnum;
import com.lowes.meetingapp.core.dao.interfaces.IEmployeeDao;
import com.lowes.meetingapp.core.exception.DAOException;
import com.lowes.meetingapp.core.dao.beans.EmployeeDO;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class EmployeeRegisterDao implements IEmployeeDao {
    Logger logger=LoggerFactory.getLogger(EmployeeRegisterDao.class);

    @Inject
    private Map<Long,EmployeeDO> employeeModelMap;



    @Override
    public Boolean register(EmployeeDO employeeDO) throws DAOException {
         Boolean resgisterdSuccessfully=false;
        if(employeeModelMap.get(employeeDO.getEmployeeId())!=null){
            throw new DAOException(ErrorEnum.ENTITY_EXISTS.getErrorMessage(),ErrorEnum.ENTITY_EXISTS);
        }
        employeeModelMap.put(employeeDO.getEmployeeId(),employeeDO);
        return resgisterdSuccessfully;
    }

    @Override
    public Boolean update(String employeeId, EmployeeDO employeeDO) throws DAOException {
        Boolean userUpdatedSuccessfully=false;
        if(employeeModelMap.get(employeeDO.getEmployeeId())==null){
            throw new DAOException(ErrorEnum.ENTITY_EXISTS.getErrorMessage(),ErrorEnum.ENTITY_EXISTS);
        }
        employeeModelMap.put(employeeDO.getEmployeeId(),employeeDO);
        return userUpdatedSuccessfully;
    }
}
