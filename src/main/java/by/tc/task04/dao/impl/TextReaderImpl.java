package by.tc.task04.dao.impl;

import by.tc.task04.dao.TextReaderDao;
import by.tc.task04.dao.exception.DaoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class TextReaderImpl implements TextReaderDao {

    private final static String path ="./src/main/resources/test.txt";
    @Override
    public List<String> read() throws DaoException {
        List<String> linesOfText;
        try {
            linesOfText = Files.readAllLines(Paths.get(path));
            linesOfText.removeAll(Collections.singleton(""));
        } catch (IOException e) {
            throw new DaoException(e);
        }
        return linesOfText;
    }
}
