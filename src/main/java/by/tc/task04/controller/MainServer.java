package by.tc.task04.controller;

import by.tc.task04.controller.exception.ServerException;

public class MainServer {

    public static void main(String[] args){

        Server server = new Server(8081);

        try {
            server.start();
        } catch (ServerException e) {
          System.err.println(e);
        }
    }



}
