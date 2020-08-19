package com.lowes.meetingapp.core.dao.interfaces;

import com.lowes.meetingapp.core.exception.DAOException;
import com.lowes.meetingapp.core.dao.beans.OfficeRoomInventoryDO;

import java.util.Map;

public interface IOfficeRoomInventoryDao {

    public void addInventory(OfficeRoomInventoryDO officeRoomInventoryDO) throws DAOException;
    public void blockRoom(String inventoryId,OfficeRoomInventoryDO officeRoomInventoryDO) throws DAOException;
    public OfficeRoomInventoryDO fetchOfficeInventory(String inventoryId) throws  DAOException;
}
