package by.tc.task04.service;

import java.util.ResourceBundle;

public class RegexResourceManager {
    private final static RegexResourceManager instance =new RegexResourceManager();

    private static ResourceBundle bundle = ResourceBundle.getBundle("regex");

    private RegexResourceManager(){}

    public RegexResourceManager getInstance(){return instance;}
    public static String getValue(String key){
        return bundle.getString(key);
    }
}
