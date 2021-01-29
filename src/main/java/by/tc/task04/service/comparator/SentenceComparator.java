package by.tc.task04.service.comparator;

import by.tc.task04.entity.Sentence;

import java.util.Comparator;

public class SentenceComparator implements Comparator<Sentence> {
    @Override
    public int compare(Sentence o1, Sentence o2) {
        if(o1.getWords().size()>o2.getWords().size())
        {
            return 10;
        }
        else if(o1.getWords().size()<o2.getWords().size())
        {
         return -10;
        }
         else return 0;
    }
}
