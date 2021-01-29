package by.tc.task04.controller;

import by.tc.task04.controller.exception.ServerException;
import by.tc.task04.entity.Request;
import by.tc.task04.entity.RequestType;
import by.tc.task04.entity.Text;
import by.tc.task04.service.ServiceFactory;
import by.tc.task04.service.TextHandlerService;
import by.tc.task04.service.TextService;
import by.tc.task04.service.exception.ServiceException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class RequestManager  {

   private Socket socket;
   private ObjectInputStream in;
   private ObjectOutputStream out;
   private boolean active =true;


    public RequestManager(Socket socket)  throws ServerException {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println(in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new ServerException(e);
        }

    }
    public void process()  {

        while (active) {
            try {
                Request request = (Request) in.readObject();
                requestManage(request);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e);
            }
        }
    }

    private void requestManage(Request request){
        Text text = null;

        RequestType type = request.getRequest();
        ServiceFactory factory =ServiceFactory.getInstance();
        TextService service =factory.getTextService();
        TextHandlerService handlerService = factory.getTextHandlerService();
        try {
             text = service.getText();

        } catch (ServiceException e) {
            System.err.println(e);
        }

        switch (type) {
            case COUNT_SENTENCES_WITH_SAME_WORDS:
                try {
                  out.writeObject(handlerService.countSentenceWithSameWords(text));
                } catch (ServiceException | IOException e) {
                    System.err.println(e);
                }
                break;
            case INCREASING_WORD_COUNT:
                try {
                    out.writeObject(handlerService.increasingWordCount(text));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case WORD_IN_FIRST_SENTENCE:
                try {
                    out.writeObject(handlerService.findWordInFirstSentence(text));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case SPECIFIED_LENGTH_WORDS:
                try {
                    out.writeObject(handlerService.specifiedLengthWords(text,(Integer) request.getParam()));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case CHANGE_WORD_IN_SENTENCES:
                try {
                    out.writeObject(handlerService.changeWordInSentence(text));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case PRINT_IN_ALPHABETIC_ORDER:
                try {
                    out.writeObject(handlerService.printInAlphabeticOrder(text,(Integer) request.getParam()));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case SORT_WORD_IN_ASCENDING_PROPORTION_OF_VOWELS:
                try {
                    out.writeObject(handlerService.sortWordInAscendingProportionOfVowels(text));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case SORT_WORD_WITH_VOWELS_IN_ALPHABETIC_ORDER:
                try {
                    out.writeObject(handlerService.sortWordWithVowelsInAlphabeticOrder(text));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case SORT_WORD_WITH_SPECIFIED_CHARACTER:
                try {
                    out.writeObject(handlerService.sortWordWithSpecifiedChar(text,(char)request.getParam()));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case WORDS_IN_LIST:
                try {
                    out.writeObject(handlerService.wordsInList(text,(List<String>) request.getParam()));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case EXCLUDE_MAX_LENGTH_SUBSTRING:
                try {
                    out.writeObject(handlerService.excludeMaxLengthSubstring(text,(char)request.getParam(),(char)request.getParam2()));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case DELETE_ALL_WORDS_GIVEN_LENGTH:
                try {
                    out.writeObject(handlerService.deleteAllWordsGivenLength(text,(int)request.getParam()));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case SORT_WORDS_IN_DESCENDING_ORDER_NUMBER_GIVEN_CHARACTER:
                try {
                    out.writeObject(handlerService.sortWordsInDescendingOrderNumberGivenCharacter(text,(char) request.getParam()));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case FIND_MAX_PALINDROME:
                try {
                    out.writeObject(handlerService.findMaxPalindrome(text));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case CONVERT_WORD:
                try {
                    out.writeObject(handlerService.convertWord(text));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case WORD_TO_SUBSTRING:
                try {
                    out.writeObject(handlerService.wordToSubstring(text,(Integer) request.getParam(),(String) request.getParam2()));
                } catch (IOException | ServiceException e) {
                    System.err.println(e);
                }
                break;
            case GET_TEXT:
                try {
                    out.writeObject(handlerService.textToString(text));
                } catch (IOException e) {
                    System.err.println(e);
                }
                break;
            case DISCONNECT:
                active = false;
                break;
        }
    }
}
