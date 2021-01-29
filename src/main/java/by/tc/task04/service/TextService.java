package by.tc.task04.service;

import by.tc.task04.entity.Text;
import by.tc.task04.service.exception.ServiceException;

public interface  TextService {

    Text getText()throws ServiceException;
}
