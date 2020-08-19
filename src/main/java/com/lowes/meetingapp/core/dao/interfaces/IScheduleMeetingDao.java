package com.lowes.meetingapp.core.dao.interfaces;

import com.lowes.meetingapp.core.exception.DAOException;

public interface IScheduleMeetingDao<T,R> {

    public T schedule(R r) throws DAOException;
}
