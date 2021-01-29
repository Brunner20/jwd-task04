package by.tc.task04.dao.impl;

import by.tc.task04.service.RegexResourceManager;
import by.tc.task04.dao.TextParserDao;
import by.tc.task04.dao.exception.DaoException;
import by.tc.task04.entity.Component;
import by.tc.task04.entity.Paragraph;
import by.tc.task04.entity.Sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParserImpl implements TextParserDao {

    private static final String SENTENCE_PATTERN = RegexResourceManager.getValue("SENTENCE_PATTERN");
    private TextParserDao sentenceParser;

    public ParagraphParserImpl() {
        sentenceParser = new SentenceParserImpl();
    }

    @Override
    public Component parse(List<String> text) throws DaoException {
        return null;
    }

    @Override
    public Component parse(String text) throws DaoException {
        List<Sentence> sentences = new ArrayList<>();
        Matcher matcher = Pattern.compile(SENTENCE_PATTERN).matcher(text);

        while (matcher.find()) {
            sentences.add((Sentence) sentenceParser.parse(matcher.group()));
        }
        return new Paragraph(sentences);
    }
}
