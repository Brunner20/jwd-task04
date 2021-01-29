package by.tc.task04.dao.exception;

public class DaoException extends  Exception{

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Exception e) {
    }
}
