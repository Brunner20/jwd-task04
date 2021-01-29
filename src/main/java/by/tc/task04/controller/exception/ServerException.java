package by.tc.task04.controller.exception;

import java.io.IOException;

public class ServerException  extends Exception{

    public ServerException() {
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    public ServerException(String errorMessage, IOException e){
        super(errorMessage,e);
    }
}
