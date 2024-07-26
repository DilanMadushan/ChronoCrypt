package com.example.ClientHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private String userName;

    private String senderName;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();

    public ClientHandler(Socket socket) {
        try {

            this.socket = socket;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            userName = dataInputStream.readUTF();
            System.out.println(userName);
            clients.add(this);
            senderName = userName;
            broadcastMessage("Server : "+ userName +" Enter the chat");

        } catch (IOException e) {
            closeEverything();
        }
    }

    public void listenMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (socket.isConnected()){
                    try {

                        String message = dataInputStream.readUTF();

                        String[] split = message.split(" :");
                        senderName = split[0];

                        broadcastMessage(message);

                    } catch (IOException e) {
                        closeEverything();
                    }
                }

            }
        }).start();
    }

    private void broadcastMessage(String message) throws IOException {
        for (ClientHandler client: clients) {

            if (!client.userName.equals(senderName)){
                client.dataOutputStream.writeUTF(message);
                client.dataOutputStream.flush();
            }
        }
    }

    private void closeEverything(){
        try {
            socket.close();
            dataOutputStream.close();
            dataInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}