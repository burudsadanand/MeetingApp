package com.lowes.meetingapp.core.service.impl;

import com.lowes.meetingapp.beans.request.EmployeeRequestBean;
import com.lowes.meetingapp.beans.response.EmployeeRegisterResponseBean;
import com.lowes.meetingapp.beans.response.Meta;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.beans.response.Status;
import com.lowes.meetingapp.core.dao.beans.*;
import com.lowes.meetingapp.core.dao.interfaces.IEmployeeCalendorDao;
import com.lowes.meetingapp.core.dao.interfaces.IEmployeeDao;
import com.lowes.meetingapp.core.exception.BusinessServiceException;
import com.lowes.meetingapp.core.exception.DAOException;
import com.lowes.meetingapp.core.service.interfaces.IEmployeeService;
import com.lowes.meetingapp.utils.DateUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    public static final Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);


    @Autowired
    private IEmployeeDao employeeDao;

    @Autowired
    private IEmployeeCalendorDao employeeCalendorDao;

    @Override
    public ResponseBean<List<EmployeeRegisterResponseBean>> registerEmployee(List<EmployeeRequestBean> employeeRequestBeanList) throws BusinessServiceException {

            List<EmployeeDO> employeeDOList=new ArrayList<>();
            buildEmployeeDO(employeeRequestBeanList,employeeDOList);
            for(EmployeeDO employeeDO:employeeDOList) {
                try {
                    employeeDao.register(employeeDO);
                    EmployeeCalendarDO employeeCalendarDO = buildEmployeeCalendor(employeeDO);
                    employeeCalendorDao.createEmployeeCalendor(employeeCalendarDO);

                }catch (DAOException e) {
                        e.printStackTrace();
                    }
            }
        return buildResponse(employeeDOList);
    }

    private void buildEmployeeDO(List<EmployeeRequestBean> employeeRequestBeanList, List<EmployeeDO> employeeDOList) {

        for(EmployeeRequestBean employeeRequestBean:employeeRequestBeanList) {
            EmployeeDO employeeDO=new EmployeeDO();
            employeeDOList.add(employeeDO);
            ContactDO contactModel = new ContactDO();
            AddressDO addressDO = new AddressDO();

            contactModel.setIsdCode(employeeRequestBean.getContactBean().getIsdCode());
            contactModel.setMobileNumber1(employeeRequestBean.getContactBean().getMobileNumber1());
            contactModel.setMobileNumber2(employeeRequestBean.getContactBean().getMobileNumber2());
            contactModel.setOfficeNumber(employeeRequestBean.getContactBean().getOfficeNumber());
            contactModel.setOfficialEmailId(employeeRequestBean.getContactBean().getOfficialEmailId());
            contactModel.setPersonalEmailId(employeeRequestBean.getContactBean().getPersonalEmailId());
            employeeDO.setContactDO(contactModel);
            employeeDO.setAddressDO(addressDO);
            employeeDO.setEmployeeId(employeeRequestBean.getEmployeeId());
            employeeDO.setFirstName(employeeRequestBean.getFirstName());
            employeeDO.setLastName(employeeRequestBean.getLastName());
            addressDO.setState(employeeRequestBean.getAddressBean().getState());
            addressDO.setCity(employeeRequestBean.getAddressBean().getCity());
            addressDO.setPinCode(employeeRequestBean.getAddressBean().getPinCode());
            addressDO.setCountry(employeeRequestBean.getAddressBean().getCountry());

        }
    }

    private EmployeeCalendarDO buildEmployeeCalendor(EmployeeDO employeeModel) {
        EmployeeCalendarDO employeeCalendarDO=new EmployeeCalendarDO();
        employeeCalendarDO.setEmployeeName(employeeModel.getFirstName());
        employeeCalendarDO.setEmployeeId(employeeModel.getEmployeeId());
        employeeCalendarDO.setEmployeeEmailId(employeeModel.getContactDO().getOfficialEmailId());
        Map<String, MeetingInfoDO> meetingInfoDOMap=new HashMap<>();
        generateEmptyCalendor(meetingInfoDOMap,employeeModel);
        employeeCalendarDO.setMeetingMap(meetingInfoDOMap);
        return  employeeCalendarDO;
    }

    private void generateEmptyCalendor(Map<String,MeetingInfoDO> employeeCalendarMap,EmployeeDO employeeDO) {

        List<LocalDate> getCaledorDates=DateUtils.generateCaledarDates(DateUtils.convertDateStringToLocalDate("2020-08-01"),
                                                                       DateUtils.convertDateStringToLocalDate("2020-09-30"));
        for(LocalDate localDate:getCaledorDates){
            MeetingInfoDO meetingInfoDO=new MeetingInfoDO();
            meetingInfoDO.setOfficeId(employeeDO.getOfficeId());
            AddressDO addressDO=new AddressDO();
            meetingInfoDO.setEmployeeAddress(addressDO);
            addressDO.setCity(employeeDO.getAddressDO().getCity());
            addressDO.setCountry(employeeDO.getAddressDO().getCountry());
            addressDO.setPinCode((employeeDO.getAddressDO().getPinCode()));
            addressDO.setState(employeeDO.getAddressDO().getState());
            List<Boolean> slots=new ArrayList<>();
            for(int i=0; i<95; i++){
                slots.add(true);
            }
            meetingInfoDO.setSlots(slots);
            employeeCalendarMap.put(DateUtils.convertDateToString(localDate),meetingInfoDO);
        }

    }

    private ResponseBean<List<EmployeeRegisterResponseBean>>  buildResponse(List<EmployeeDO> employeeModellist) {
        ResponseBean<List<EmployeeRegisterResponseBean>> responseBeanResponseBean=new ResponseBean<>();
        List<EmployeeRegisterResponseBean> employeeRegisterResponseBeans=new ArrayList<>();
        for(EmployeeDO employeeDO:employeeModellist) {
            EmployeeRegisterResponseBean employeeRegisterResponseBean = new EmployeeRegisterResponseBean();
            employeeRegisterResponseBean.setEmployeeEmailId(employeeDO.getContactDO().getOfficialEmailId());
            employeeRegisterResponseBean.setEmployeName(employeeDO.getFirstName());
            employeeRegisterResponseBeans.add(employeeRegisterResponseBean);
        }
        Meta meta=new Meta();
        meta.setResponseTimeInMillis(System.currentTimeMillis());
        meta.setStatus(Status.SUCCESS);
        responseBeanResponseBean.setResponse(employeeRegisterResponseBeans);
        responseBeanResponseBean.setMeta(meta);

        return responseBeanResponseBean;
    }

    @Override
    public EmployeeDO getEmployee(Long emloyeeId) throws BusinessServiceException {
        return null;
    }

    @Override
    public List<EmployeeDO> getListOfEmployees(Long officeId) throws BusinessServiceException {
        return null;
    }
}
