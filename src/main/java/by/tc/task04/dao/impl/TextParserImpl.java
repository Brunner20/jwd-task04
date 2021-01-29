package by.tc.task04.dao.impl;

import by.tc.task04.dao.TextParserDao;
import by.tc.task04.dao.exception.DaoException;
import by.tc.task04.entity.CodeBlock;
import by.tc.task04.entity.Component;
import by.tc.task04.entity.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextParserImpl implements TextParserDao {


    private TextParserDao paragraphParser;
    private String currentLine;

    public TextParserImpl() {
        paragraphParser = new ParagraphParserImpl();
    }

    @Override
    public Text parse(List<String> text) throws DaoException {

        List<Component> components = new ArrayList<>();
        Iterator<String> iterator = text.iterator();

        while (iterator.hasNext()){

            currentLine= iterator.next();
            
            if(currentLine.contains("{"))
                components.add(parseCode(currentLine,iterator));
            else {
                components.add(parseParagraph(currentLine,iterator));
                if(currentLine.contains("{"))
                    components.add(parseCode(currentLine,iterator));}

        }
        return new Text(components);
    }

    @Override
    public Component parse(String text) throws DaoException {
        return null;
    }

    private Component parseParagraph(String line, Iterator<String> iterator) throws DaoException {


        StringBuilder partTextParagraph = new StringBuilder();
        partTextParagraph.append(line);

        while(iterator.hasNext()){
            currentLine= iterator.next();
            if(currentLine.contains("{")){

                return paragraphParser.parse(partTextParagraph.toString());
            }
            partTextParagraph.append(currentLine);
        }

        return paragraphParser.parse(partTextParagraph.toString());
    }

    private Component parseCode(String line, Iterator<String> iterator) {

        CodeBlock block = new CodeBlock();
        StringBuilder linesOfCodeBlock = new StringBuilder();
        linesOfCodeBlock.append(line);

        int openedBracket = countOpenedBracket(line);
        int closedBracket = countClosedBracket(line);

        if(closedBracket !=openedBracket){
            while (iterator.hasNext()){
                currentLine=iterator.next();
                 openedBracket += countOpenedBracket(currentLine);
                 closedBracket += countClosedBracket(currentLine);
                 linesOfCodeBlock.append(currentLine);

                 if(openedBracket==closedBracket){
                     block.setCode(linesOfCodeBlock.toString());
                     return block;
                 }
            }
        }
        block.setCode(linesOfCodeBlock.toString());
        return block;
    }

    private int countOpenedBracket(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{') {
                count++;
            }
        }
        return count;
    }

    private int countClosedBracket(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '}') {
                count++;
            }
        }
        return count;
    }

}
