package com.lowes.meetingapp;

import com.lowes.meetingapp.adaptor.IMeetingAdaptor;
import com.lowes.meetingapp.adaptor.employeecalendor.BlockEmployeeCalendorAdaptor;
import com.lowes.meetingapp.adaptor.employeecalendor.EmployeeAvailabilityCheckAdaptor;
import com.lowes.meetingapp.adaptor.schedulemeeting.BlockMeetingRoomAdapter;
import com.lowes.meetingapp.core.dao.beans.EmployeeCalendarDO;
import com.lowes.meetingapp.core.dao.beans.EmployeeDO;
import com.lowes.meetingapp.core.dao.beans.MeetingScheduleDO;
import com.lowes.meetingapp.core.dao.beans.OfficeRoomInventoryDO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class MeetingappApplication {

	@Autowired
	@Resource(name="employeeAvailabilityCheckAdaptor")
	private IMeetingAdaptor employeeAvailabilityCheckAdaptor;

	@Autowired
	@Resource(name="blockMeetingRoomAdapter")
	private IMeetingAdaptor blockMeetingRoomAdapter;

	@Autowired
	@Resource(name="blockEmployeeCalendorAdaopter")
	private IMeetingAdaptor blockEmployeeCalendorAdaptor;



	public static void main(String[] args) {
		SpringApplication.run(MeetingappApplication.class, args);
	}


	@Bean
	public Map<String, OfficeRoomInventoryDO> officeModelMap(){
		Map<String, OfficeRoomInventoryDO> officeModelMap=new HashMap<>();
		return officeModelMap;
	}


	@Bean
	public LinkedHashMap<String, IMeetingAdaptor> scheduleMeetingAdaptorMap(){
		LinkedHashMap<String, IMeetingAdaptor> scheduleMeetingAdaptorMap=new LinkedHashMap<>();
		scheduleMeetingAdaptorMap.put("employeeAvailabilityAdaptor",employeeAvailabilityCheckAdaptor);
		scheduleMeetingAdaptorMap.put("blockMeetingRoomAdaptor",blockMeetingRoomAdapter);
		scheduleMeetingAdaptorMap.put("blockEmployeeCalendorAdaptor",blockEmployeeCalendorAdaptor);

		return scheduleMeetingAdaptorMap;
	}

	@Bean
	public  Map<Long, MeetingScheduleDO> scheduledMeetingMap(){
		Map<Long,MeetingScheduleDO> scheduleDOMap=new HashMap<>();
		return  scheduleDOMap;
	}

	@Bean
	public  Map<Long, EmployeeDO> employeeModelMap(){
		 Map<Long,EmployeeDO> employeeModelMap=new HashMap<>();
		return  employeeModelMap;
	}

	@Bean
	public  Map<String, EmployeeCalendarDO> employeeCalendarDOMap(){
		Map<String,EmployeeCalendarDO> employeeCalendarDOMap=new HashMap<>();
		return  employeeCalendarDOMap;
	}

	@Bean
	public ExecutorService createExecutorService(){
		return Executors.newFixedThreadPool(100);
	}

}
