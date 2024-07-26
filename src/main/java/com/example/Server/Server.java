package com.example.Server;

import com.example.ClientHandler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public  static  ServerSocket serverSocket;
    public static void main(String[] args) {

        try{
            serverSocket = new ServerSocket(999);
        }catch (Exception e){
            e.printStackTrace();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!serverSocket.isClosed()){
                    try{
                        System.out.println("Server is Started");
                        Socket socket = serverSocket.accept();
                        System.out.println("New user connected");
                        ClientHandler clientHandler = new ClientHandler(socket);
                        clientHandler.listenMessage();

                    }catch(IOException e){
                        e.printStackTrace();
                    }


                }
            }
        }).start();
    }
}
