package by.tc.task04.service;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.service.exception.ServiceException;

import java.util.List;

public interface TextHandlerService {



    public int countSentenceWithSameWords(Text text) throws ServiceException;
    public List<Sentence> increasingWordCount(Text text) throws ServiceException;
    public String findWordInFirstSentence(Text text) throws ServiceException;
    public List<Word> specifiedLengthWords(Text text, int length) throws ServiceException;
    public List<Sentence> changeWordInSentence(Text text) throws ServiceException;

    public List<Word> printInAlphabeticOrder(Text text,int length) throws ServiceException;
    public List<Word> sortWordInAscendingProportionOfVowels(Text text) throws ServiceException;
    public List<Word> sortWordWithVowelsInAlphabeticOrder(Text text) throws ServiceException;
    public List<Word> sortWordWithSpecifiedChar(Text text, char character)throws ServiceException;
    public List<Word>  wordsInList(Text text,List<String> list) throws ServiceException;
    public List<Sentence> excludeMaxLengthSubstring(Text text, char begin, char end) throws ServiceException;
    public List<Word> deleteAllWordsGivenLength(Text text, int length) throws ServiceException;
    public List<Word> sortWordsInDescendingOrderNumberGivenCharacter(Text text, char character) throws ServiceException;
    public String findMaxPalindrome(Text text) throws ServiceException ;
    public List<Word> convertWord(Text text) throws ServiceException ;
    public String wordToSubstring(Text text,int length,String substring) throws ServiceException ;
    public String textToString(Text text);

}
