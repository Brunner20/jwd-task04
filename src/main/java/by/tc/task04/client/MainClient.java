package by.tc.task04.client;

import by.tc.task04.client.exception.ClientException;

public class MainClient {

    public static void main(String[] args){

        Client client = new Client("localhost",8081);
        try {
            client.connect();
            client.changeWordInSentence();
            client.disconnect();
        } catch (ClientException e) {
            System.err.println(e);
        }
    }
}
