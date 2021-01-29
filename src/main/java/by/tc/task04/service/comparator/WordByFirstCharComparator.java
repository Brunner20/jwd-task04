package by.tc.task04.service.comparator;

import by.tc.task04.entity.Word;

import java.util.Comparator;

public class WordByFirstCharComparator implements Comparator<Word> {


    @Override
    public int compare(Word a, Word b) {
        Character consonantA = a.getWord().toLowerCase().charAt(0);
        Character consonantB = b.getWord().toLowerCase().charAt(0);
        return consonantA.compareTo(consonantB);
    }
}
