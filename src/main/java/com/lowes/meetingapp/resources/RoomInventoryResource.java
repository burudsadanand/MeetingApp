package com.lowes.meetingapp.resources;

import com.google.gson.Gson;
import com.lowes.meetingapp.beans.response.OfficeMeetingRoomInventaryBean;
import com.lowes.meetingapp.beans.response.ResponseBean;
import com.lowes.meetingapp.beans.response.RoomInventoryResponse;
import com.lowes.meetingapp.core.dao.beans.OfficeRoomInventoryDO;
import com.lowes.meetingapp.core.service.interfaces.IOfficeRoomInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

@RestController
@RequestMapping(name="/meetingApp")
public class RoomInventoryResource {

    private static final Logger logger=LoggerFactory.getLogger(RoomInventoryResource.class);

    @Autowired
    private IOfficeRoomInventoryService officeRoomInventoryService;

    @Inject
    private Map<String,OfficeRoomInventoryDO> officeRoomInventoryDOMap;

    @PostMapping(value="/addInventory")
    public ResponseEntity<ResponseBean<RoomInventoryResponse>> addInventory(@RequestBody OfficeMeetingRoomInventaryBean officeMeetingRoomInventaryBean){
        ResponseBean<RoomInventoryResponse> response=  officeRoomInventoryService.addRoomInventory(officeMeetingRoomInventaryBean);
        for(Map.Entry<String,OfficeRoomInventoryDO> entry:officeRoomInventoryDOMap.entrySet()){
            Gson gson=new Gson();
            logger.info("OfficeId  {}", entry.getKey());
            logger.info("Inventory {}", gson.toJson(entry.getValue()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
