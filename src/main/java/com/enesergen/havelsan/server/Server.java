package com.enesergen.havelsan.server;

import com.enesergen.havelsan.model.Model;
import com.enesergen.havelsan.model.UpdateData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket server = null;
                try {
                    server = new ServerSocket(4444);
                    server.setReuseAddress(true);
                    while (true) {
                        Socket client = server.accept();
                        client.setKeepAlive(true);
                        System.out.println("New Client Connected:" +
                                client.getInetAddress().getHostAddress());
                        ClientHandler clientHandler=new ClientHandler(client);
                        new Thread(clientHandler).start();
                    }

                } catch (SocketException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;

        ClientHandler(Socket socket) {
            this.socket=socket;
        }

        @Override
        public void run() {
            try(ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());){
                while(true){
                    ArrayList<Model>receivedDataFromClient =(ArrayList<Model>)ois.readObject();
                    receivedDataFromClient.forEach(item->{
                        System.out.println(item.toString());
                    });
                    UpdateData.updateData(receivedDataFromClient);
                    oos.writeObject(receivedDataFromClient);
                }


            }catch (IOException | ClassNotFoundException e ){
                System.out.println(e.getMessage());
            }
        }
    }
}
//
