package com.lowes.meetingapp.core.dao.impl;

import com.lowes.meetingapp.core.constants.ErrorEnum;
import com.lowes.meetingapp.core.dao.beans.OfficeRoomInventoryDO;
import com.lowes.meetingapp.core.dao.interfaces.IOfficeRoomInventoryDao;
import com.lowes.meetingapp.core.exception.DAOException;
import com.lowes.meetingapp.utils.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;


@Component
public class OfficeRoomInventoryDao implements IOfficeRoomInventoryDao {
    Logger logger=LoggerFactory.getLogger(OfficeRoomInventoryDao.class);

    @Inject
    private Map<String, OfficeRoomInventoryDO> officeModelMap;

        private List<String> getDocumentIdList(Long officeId,OfficeRoomInventoryDO officeRoomInventoryDO){
        return IdGenerator.generateOfficeIntenvetoryId(officeId,officeRoomInventoryDO.getFromDate(),officeRoomInventoryDO.getToDate());
    }

    @Override
    public void addInventory(OfficeRoomInventoryDO officeRoomInventoryDO) throws DAOException {
        List<String> documentIdList=getDocumentIdList(officeRoomInventoryDO.getOfficeId(),officeRoomInventoryDO);
        documentIdList.stream().forEach(documentId->{
            officeModelMap.put(documentId,officeRoomInventoryDO);
        });
    }

    @Override
    public void blockRoom(String inventoryId,OfficeRoomInventoryDO officeRoomInventoryDO) {
        officeModelMap.put(inventoryId,officeRoomInventoryDO);
    }

    @Override
    public OfficeRoomInventoryDO fetchOfficeInventory(String inventoryId) throws DAOException {
            if(officeModelMap.get(inventoryId)==null){
                throw new DAOException(ErrorEnum.ENTITY_NOTEXISTS.getErrorMessage(),ErrorEnum.ENTITY_NOTEXISTS);
            }else{
                return officeModelMap.get(inventoryId);
            }
    }
}
