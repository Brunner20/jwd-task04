package by.tc.task04.dao.impl;

import by.tc.task04.service.RegexResourceManager;
import by.tc.task04.dao.TextParserDao;
import by.tc.task04.dao.exception.DaoException;
import by.tc.task04.entity.Component;
import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParserImpl implements TextParserDao {

    private static final String WORD_PATTERN = RegexResourceManager.getValue("WORD_PATTERN");

    @Override
    public Component parse(List<String> text) throws DaoException {
       return null;
    }

    @Override
    public Component parse(String text) throws DaoException {
        List<Word> words = new ArrayList<>();
        Matcher matcher =  Pattern.compile(WORD_PATTERN).matcher(text);
        while(matcher.find()){
            words.add(new Word(matcher.group()));
        }
        return new Sentence(words);
    }
}
