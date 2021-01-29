package by.tc.task04.service.impl;

import by.tc.task04.service.RegexResourceManager;
import by.tc.task04.dao.exception.DaoException;
import by.tc.task04.dao.impl.SentenceParserImpl;
import by.tc.task04.entity.*;
import by.tc.task04.service.TextHandlerService;
import by.tc.task04.service.comparator.*;
import by.tc.task04.service.exception.ServiceException;

import java.util.*;

public class TextHandlerServiceImpl implements TextHandlerService {


    private final static String VOWELS = RegexResourceManager.getValue("VOWELS");
    private final static String CONSONANTS = RegexResourceManager.getValue("CONSONANTS");

    @Override
    public int countSentenceWithSameWords(Text text) {

       List<Sentence> sentenceList = getAllSentence(text);
        int count=0;
        for(Sentence sentence: sentenceList)
        {
            List<Word> words =sentence.getWords();
            int length = words.size();
            for(int i =0;i<length;i++)
            {
                Word word = words.get(0);
                words.remove(0);
                if(words.contains(word)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }



    @Override
    public List<Sentence> increasingWordCount(Text text) {

       List<Sentence> sentences = getAllSentence(text);
        sentences.sort(new SentenceComparator());
        return sentences;
    }

    @Override
    public String findWordInFirstSentence(Text text)  {
        List<Sentence> sentenceList= getAllSentence(text);
        List<Word> firstSentence = sentenceList.get(0).getWords();
        List<Word> allWords = new ArrayList<>();

        for (int i =1;i<sentenceList.size();i++){
            allWords.addAll(sentenceList.get(i).getWords());
        }

        boolean isAnswer= true;
        for(Word wordInFirstSentence:firstSentence){
            for(Word word: allWords){
                if(word.getWord().equalsIgnoreCase(wordInFirstSentence.getWord())){
                    isAnswer =false;
                    break;
                }
            }
            if(isAnswer)
            return wordInFirstSentence.getWord();
            else isAnswer = true;
        }
        return null;
    }

    @Override
    public List<Word> specifiedLengthWords(Text text, int length)  {
        List<Sentence> sentenceList= getAllSentence(text);
        Set<Word> wordsToAnswer = new HashSet<>();

        for(Sentence sentence:sentenceList){
            List<Word> wordsInSentence = sentence.getWords();
            if(wordsInSentence.get(wordsInSentence.size()-1).getWord().equalsIgnoreCase("?")){
                for (Word word:wordsInSentence){
                    if (word.getWord().length()==length)
                        wordsToAnswer.add(word);
                }
            }
        }

        return new ArrayList<>(wordsToAnswer);
    }

    @Override
    public List<Sentence> changeWordInSentence(Text text) {
        List<Sentence> sentenceList= getAllSentence(text);

        for (Sentence sentence:sentenceList){
            List<Word> words = sentence.getWords();
            Word word = words.get(0);
            words.set(0, words.get(words.size() - 2));
            words.set(words.size() - 2, word);
        }

        return sentenceList;
    }

    @Override
    public List<Word> printInAlphabeticOrder(Text text,int length){
        List<Word> words =getAllWords(text);
        words.sort(new WordByFirstCharComparator());
        return words;
    }

    @Override
    public List<Word> sortWordInAscendingProportionOfVowels(Text text)  {
        List<Word> words =getAllWords(text);
        words.sort(new WordByVowelsProportionComparator());
        return words;
    }

    @Override
    public List<Word> sortWordWithVowelsInAlphabeticOrder(Text text)  {
        List<Word> words =getAllWords(text);
        List<Word> answer =getWordsWithFirstVowel(words);
        answer.sort(new WordByFirstConsonantComparator());
        return answer;
    }

    @Override
    public List<Word> sortWordWithSpecifiedChar(Text text, char character)  {
        List<Word> words =getAllWords(text);
        words.sort(new WordByCharAndAlphabetComparator(character));
        return words;
    }

    @Override
    public List<Word> wordsInList(Text text,List<String> list)  {
        List<Sentence> sentenceList =getAllSentence(text);
        SortedMap<Integer,Word> wordMap = new TreeMap<>();
        int count=0;
        for (String word:list) {
            for (Sentence sentence : sentenceList) {
                for (Word wordInSentence : sentence.getWords()) {
                    if (word.equalsIgnoreCase(wordInSentence.getWord()))
                        count++;
                }
            }
            wordMap.put(count,new Word(word));
            count=0;
        }
       List<Word> answer= new ArrayList<>(wordMap.values());
        Collections.reverse(answer);
        return  answer;
    }

    @Override
    public List<Sentence> excludeMaxLengthSubstring(Text text, char begin, char end) throws ServiceException {
        List<Sentence> sentenceList = getAllSentence(text);
        List<Sentence> answer = new ArrayList<>();
        for(Sentence sentence:sentenceList){
            String unitedWords = doSentenceFromWords(sentence);
            int beginIndex = unitedWords.indexOf( begin);
            int endIndex = unitedWords.lastIndexOf(end);
            if (beginIndex < 0 || endIndex < 0) {
                continue;
            }
            String subString =unitedWords.substring(beginIndex,endIndex);
            unitedWords= unitedWords.replaceAll(subString,"");
            try {
                sentence= (Sentence) new SentenceParserImpl().parse(unitedWords);
                answer.add(sentence);
            } catch (DaoException e) {
               throw new ServiceException(e);
            }
        }
        return answer;
    }

    private String doSentenceFromWords(Sentence sentence){
        List<Word> words = sentence.getWords();
        StringBuilder stringBuilder = new StringBuilder();
        for (Word word : words) {
            stringBuilder.append(word.getWord()).append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public List<Word> deleteAllWordsGivenLength(Text text, int length)  {
        List<Word> wordList= getAllWords(text);
        List<Word> answer = new ArrayList<>(wordList);
        for(Word word:wordList){
            String wordString = word.getWord();
            if(CONSONANTS.indexOf(Character.toUpperCase(wordString.charAt(0)))>-1&&wordString.length()==length){
                answer.remove(word);
            }
        }
        return answer;
    }

    @Override
    public List<Word> sortWordsInDescendingOrderNumberGivenCharacter(Text text, char character)  {
        List<Word> words =getAllWords(text);
        words.sort(new WordByCharAndAlphabetComparator(character));
        Collections.reverse(words);
        return words;
    }

    @Override
    public String findMaxPalindrome(Text text)  {
        List<Sentence> sentenceList = getAllSentence(text);
        String maxPalindrome="";
        for(Sentence sentence:sentenceList){
            String unitedWords = doSentenceFromWords(sentence);
            String palindrome = longestPalindromeString(unitedWords);
            if(maxPalindrome.length()<palindrome.length())
                maxPalindrome=palindrome;
        }
        return maxPalindrome;
    }

    private  String longestPalindromeString(String in) {
       String maxPalindrome="";
        for (int i = 0; i < in.length(); i++) {
            for (int j = i + 1; j <= in.length(); j++) {
                if (isPalindrome(in.substring(i, j))) {
                    String palindrome=in.substring(i, j);
                    if(maxPalindrome.length()<palindrome.length())
                        maxPalindrome=palindrome;
                }
            }
        }
        return maxPalindrome;
    }

    private boolean isPalindrome(String input) {
        StringBuilder plain = new StringBuilder(input);
        StringBuilder reverse = plain.reverse();
        return (reverse.toString()).equals(input);
    }

    @Override
    public List<Word> convertWord(Text text)  {
        List<Word> wordList = getAllWords(text);
        List<Word> answer = new ArrayList<>();
        String toWord="";
        for(Word word:wordList){
            String wordString = word.getWord();
            char first = wordString.charAt(0);
            char last =wordString.charAt(wordString.length()-1);

             if(wordString.length()!=1)
             toWord = first +
                    wordString.replaceAll(String.valueOf(first),"").
                               replaceAll(String.valueOf(last),"")+ last;

           answer.add(new Word(toWord));
        }
        return null;
    }

    @Override
    public String wordToSubstring(Text text,int length,String substring)  {
        List<Sentence> sentenceList = getAllSentence(text);
        for(Sentence sentence:sentenceList){
            for(Word word:sentence.getWords()){
                if(word.getWord().length()==length)
                    word.setWord(substring);
            }
        }
        return null;
    }

    @Override
    public String textToString(Text text){
        StringBuilder newText =new StringBuilder() ;
        for(Component component:text.getText()){
            if(component instanceof Paragraph){
                for(Sentence sentence:((Paragraph) component).getSentences()){
                    for(Word word:sentence.getWords())
                        newText.append(word.getWord()).append(" ");
                }
            }else{
                CodeBlock block=   (CodeBlock)component;
                newText.append("\n").append(block.getCode()).append("\n");
            }
        }
        return newText.toString();
    }

    private List<Sentence> getAllSentence(Text text){
        List<Sentence> sentenceList = new ArrayList<>();
        for(Component component:text.getText()){
            if(component instanceof Paragraph){
                Paragraph paragraph=(Paragraph) component;
                sentenceList.addAll(paragraph.getSentences());
            }
        }
        return sentenceList;
    }

    private List<Word> getWordsWithFirstVowel(List<Word> wordList){
        Set<Word> answer =new HashSet<>();
       for (Word word:wordList)
       {

           if( VOWELS.indexOf(Character.toUpperCase(word.getWord().charAt(0)))>-1)
               answer.add(word);
        }

       return new ArrayList<>(answer);

    }

    private List<Word> getAllWords(Text text){
        Set<Word> wordList = new HashSet<>();
        List<Sentence> sentenceList = getAllSentence(text);

        for(Sentence sentence:sentenceList){
            wordList.addAll(sentence.getWords());
        }

        wordList.remove(new Word("."));
        wordList.remove(new Word(","));
        wordList.remove(new Word("?"));
        wordList.remove(new Word("!"));
        return new ArrayList<>(wordList);
    }



}
