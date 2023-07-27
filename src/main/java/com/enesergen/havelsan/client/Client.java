package com.enesergen.havelsan.client;

import com.enesergen.havelsan.model.GenerateData;
import com.enesergen.havelsan.model.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

public class Client extends Thread {
    static ArrayList<Model> data = GenerateData.generateData();
    static Socket socket = null;
    static ObjectOutputStream oos=null;
    static ObjectInputStream ois=null;
    @Override
    public void run() {
        super.run();
    }

    public static void main(String[] args) {
        boolean condition = true;
        while (condition) {
            try {
                socket = new Socket("localhost", 4444);
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                condition = false;
            } catch (IOException e) {
                System.out.println(" Yukarı Could not connect to server! Retrying....");
            }
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean connected=true;
                while (true) {
                    try {
                        if(connected){
                            oos.writeObject(data);
                            ArrayList<Model> updatedDataFromServer = (ArrayList<Model>) ois.readObject();
                            updatedDataFromServer.forEach((item) -> {
                                System.out.println(item.toString());
                            });
                            data = updatedDataFromServer;
                        }else{
                            socket=new Socket("localhost",4444);
                            oos = new ObjectOutputStream(socket.getOutputStream());
                            ois = new ObjectInputStream(socket.getInputStream());
                            connected=true;
                        }

                    } catch (UnknownHostException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        System.out.println("Could not connect to server! Retrying....");
                        connected=false;
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }).start();

    }
}
