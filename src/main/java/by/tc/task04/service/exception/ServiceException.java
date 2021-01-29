package by.tc.task04.service.exception;

public class ServiceException extends  Exception{

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception e) {
    }
}
