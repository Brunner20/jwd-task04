package by.tc.task04.dao;

import by.tc.task04.dao.exception.DaoException;
import by.tc.task04.entity.Component;
import by.tc.task04.entity.Text;

import java.util.List;

public interface TextParserDao {

    Component parse(List<String> text) throws DaoException;
    Component parse(String text) throws DaoException;
}
