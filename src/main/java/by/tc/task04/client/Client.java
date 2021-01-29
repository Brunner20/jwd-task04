package by.tc.task04.client;

import by.tc.task04.client.exception.ClientException;
import by.tc.task04.entity.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Client {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws ClientException {
        try {
            socket = new Socket(host,port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
           throw new ClientException(e);
        }
    }

    public int countSentenceWithSameWords() throws ClientException {
        try {
            out.writeObject(new Request(RequestType.COUNT_SENTENCES_WITH_SAME_WORDS));
            return (int) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public List<Sentence> increasingWordCount() throws ClientException {
        try {
            out.writeObject(new Request(RequestType.INCREASING_WORD_COUNT));
            return (List<Sentence>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public String findWordInFirstSentence() throws ClientException {
        try {
            out.writeObject(new Request(RequestType.WORD_IN_FIRST_SENTENCE));
            return (String) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }

    public List<Word> specifiedLengthWords(int length) throws ClientException {
        try {
            out.writeObject(new Request(RequestType.SPECIFIED_LENGTH_WORDS,length));
            return (List<Word>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public List<Word> changeWordInSentence() throws ClientException {
        try {
            out.writeObject(new Request(RequestType.CHANGE_WORD_IN_SENTENCES));
            return (List<Word>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public List<Word>  printInAlphabeticOrder(int length) throws ClientException {
        try {
            out.writeObject(new Request(RequestType.PRINT_IN_ALPHABETIC_ORDER));
            return (List<Word> ) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }

    public List<Word> sortWordInAscendingProportionOfVowels() throws ClientException {
        try {
            out.writeObject(new Request(RequestType.SORT_WORD_IN_ASCENDING_PROPORTION_OF_VOWELS));
            return (List<Word>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public String sortWordWithVowelsInAlphabeticOrder() throws ClientException {
        try {
            out.writeObject(new Request(RequestType.SORT_WORD_WITH_VOWELS_IN_ALPHABETIC_ORDER));
            String sentences =(String) in.readObject();
            return sentences;
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public List<Word> sortWordWithSpecifiedChar(char character) throws ClientException {
        try {
            out.writeObject(new Request(RequestType.SORT_WORD_WITH_SPECIFIED_CHARACTER,character));
            return (List<Word>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public List<Word> wordsInList(List<String> list) throws ClientException {
        try {
            out.writeObject(new Request(RequestType.WORDS_IN_LIST,list));
            return (List<Word>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public List<Sentence> excludeMaxLengthSubstring(char begin,char end) throws ClientException {
        try {
            out.writeObject(new Request(RequestType.EXCLUDE_MAX_LENGTH_SUBSTRING,begin,end));
            return (List<Sentence>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public List<Word> deleteAllWordsGivenLength(int length) throws ClientException {
        try {
            out.writeObject(new Request(RequestType.DELETE_ALL_WORDS_GIVEN_LENGTH,length));
            return (List<Word>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public List<Word> sortWordsInDescendingOrderNumberGivenCharacter(char character) throws ClientException {
        try {
            out.writeObject(new Request(RequestType.SORT_WORDS_IN_DESCENDING_ORDER_NUMBER_GIVEN_CHARACTER,character));
            return (List<Word>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }

    public String findMaxPalindrome() throws ClientException {
        try {
            out.writeObject(new Request(RequestType.FIND_MAX_PALINDROME));
            String sentences =(String) in.readObject();
            return sentences;
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public List<Word> convertWord() throws ClientException {
        try {
            out.writeObject(new Request(RequestType.CONVERT_WORD));
            return (List<Word>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public String wordToSubstring(String substring) throws ClientException {
        try {
            out.writeObject(new Request(RequestType.WORD_TO_SUBSTRING,substring));
            String sentences =(String) in.readObject();
            return sentences;
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }
    public String getText() throws ClientException {
        try {
            out.writeObject(new Request(RequestType.GET_TEXT));
            return (String) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientException(e);
        }
    }

    public void disconnect() throws ClientException {
        try {
            in.close();
            out.close();
            out.writeObject(new Request(RequestType.DISCONNECT));
            socket.close();
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }
}
