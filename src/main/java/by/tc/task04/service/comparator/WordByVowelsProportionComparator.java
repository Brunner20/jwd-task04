package by.tc.task04.service.comparator;

import by.tc.task04.service.RegexResourceManager;
import by.tc.task04.entity.Word;

import java.util.Comparator;

public class WordByVowelsProportionComparator implements Comparator<Word> {

    private final static String VOWELS = RegexResourceManager.getValue("VOWELS");

    private static boolean isVowel(Character c) {
        return VOWELS.indexOf(Character.toUpperCase(c)) > -1;
    }

    private double getProportion(String word){
        double vowels =0;
        for (int i = 0; i < word.length(); ++i)
            if (isVowel(word.charAt(i)))
               vowels++;
        return vowels/word.length();
    }

    @Override
    public int compare(Word o1, Word o2) {
        double prop1 = getProportion(o1.getWord());
        double prop2 = getProportion(o2.getWord());
        if(prop1>prop2){
            return 10;
        }else if(prop1<prop2){
            return -10;
        }
        else
        return 0;
    }
}
