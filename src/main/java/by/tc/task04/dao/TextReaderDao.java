package by.tc.task04.dao;

import by.tc.task04.dao.exception.DaoException;

import java.util.List;

public interface TextReaderDao {


    List<String> read() throws DaoException;
}
