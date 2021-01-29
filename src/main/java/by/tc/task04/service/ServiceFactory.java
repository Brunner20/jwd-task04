package by.tc.task04.service;

import by.tc.task04.service.impl.TextHandlerServiceImpl;
import by.tc.task04.service.impl.TextServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance =  new ServiceFactory();

    private final TextService service = new TextServiceImpl();
    private final TextHandlerService textHandlerService = new TextHandlerServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance;
    }

    public TextService getTextService(){
        return service;
    }

    public TextHandlerService getTextHandlerService() {
        return textHandlerService;
    }
}
