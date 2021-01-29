package by.tc.task04.service.comparator;

import by.tc.task04.service.RegexResourceManager;
import by.tc.task04.entity.Word;

import java.util.Comparator;

public class WordByFirstConsonantComparator implements Comparator<Word> {

    private final static String CONSONANTS = RegexResourceManager.getValue("CONSONANTS");

    private  boolean isConsonant(Character c) {
        return CONSONANTS.indexOf(Character.toUpperCase(c)) > -1;
    }

    private Character getFirstConsonant(String word){
        for (int i = 0; i < word.length(); ++i)
            if (isConsonant(word.charAt(i)))
                return word.charAt(i);

            return Character.MIN_VALUE;
    }

    @Override
    public int compare(Word o1, Word o2) {

        Character ch1 = getFirstConsonant(o1.getWord());
        Character ch2 = getFirstConsonant(o2.getWord());

        return Character.compare(ch1,ch2);
    }
}
