package by.tc.task04.service.impl;

import by.tc.task04.dao.TextParserDao;
import by.tc.task04.dao.TextReaderDao;
import by.tc.task04.dao.exception.DaoException;
import by.tc.task04.dao.impl.TextParserImpl;
import by.tc.task04.dao.impl.TextReaderImpl;
import by.tc.task04.entity.Component;
import by.tc.task04.entity.Text;
import by.tc.task04.service.TextService;
import by.tc.task04.service.exception.ServiceException;

import java.util.List;

public class TextServiceImpl implements TextService {
    @Override
    public Text getText() throws ServiceException {

        TextReaderDao readerDao = new TextReaderImpl();
        TextParserDao parserDao = new TextParserImpl();
        Component text;
        try {
            List<String> lines = readerDao.read();
            text = parserDao.parse(lines);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return (Text)text;
    }


}
