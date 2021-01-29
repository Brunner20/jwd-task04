package by.tc.task04.service.comparator;

import by.tc.task04.entity.Word;

import java.util.Comparator;
import java.util.Locale;

public class WordByCharAndAlphabetComparator implements Comparator<Word> {

    private Character character;

    public WordByCharAndAlphabetComparator(Character  character) {
        this.character=character;
    }

    @Override
    public int compare(Word o1, Word o2) {

       int ch1= getCharCount(o1.getWord().toLowerCase(Locale.ROOT));
       int ch2= getCharCount(o2.getWord().toLowerCase(Locale.ROOT));
       if(ch1>ch2){
           return 10;
       }
       else if(ch1<ch2){
           return -10;
       }
          return o1.getWord().compareTo(o2.getWord());

    }

    private int getCharCount(String word) {
        int count =0;
        for (int i = 0; i < word.length(); ++i)
            if (character.compareTo(word.charAt(i))==0)
               count++;
            return count;
    }
}
