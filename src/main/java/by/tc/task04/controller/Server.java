package by.tc.task04.controller;

import by.tc.task04.controller.exception.ServerException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws ServerException {
        try {
            serverSocket =new ServerSocket(port);
            Socket acceptSocket =  serverSocket.accept();
            RequestManager newManager = new RequestManager(acceptSocket);
            newManager.process();
        } catch (IOException e) {
           throw  new ServerException(e);
        }
    }

    public void stop() throws ServerException {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new ServerException(e);
        }
    }
}
