package com;

import com.Utils.Socketer;

import java.io.IOException;
import java.net.Socket;


public class Main {

    private static Socket socket;

    public static void main(String[] args) throws IOException {
//        User orderer = null;
//        try {
//
//            ServerSocket serverSocket = new ServerSocket();
//            int port = 8888;
//
//            //binding created server socket with host and port number
//            serverSocket.bind(new InetSocketAddress("10.22.33.102" , 8888));
//            System.out.println("Server started and listening to the 8888");
//
//            //Server is running always due to infinite loop
//            while(true) {
//                String response = null;
//                String messageReceived = null;
//                try {
//                    //Reading message sent by client
//                    socket = serverSocket.accept();
//                    System.out.println("Client connected on port 8888");
//                    InputStream inStream = socket.getInputStream();
//                    InputStreamReader inStreamReader = new InputStreamReader(inStream);
//                    BufferedReader buffReader = new BufferedReader(inStreamReader);
//                    messageReceived = buffReader.readLine();
//                    System.out.println("Message received from client is " + messageReceived);
//
//
//                    //Executing operation on received message
//
//                    Gson gson = new Gson();
//                    //Error in input format - messageReceived
//                    //User orderer = new User(messageReceived);
//                    orderer = gson.fromJson(messageReceived, User.class);
//
//
//                    //problem is that this object of Gson class wont get null properties
//                    System.out.println(orderer.getMail());
//
//
//                    response = gson.toJson(orderer);
//
//
//                } catch (Exception e) {
//                    /*int receivedNumber = Integer.parseInt(messageReceived);
//                    int returnValue = receivedNumber * 2;
//                    response = String.valueOf(returnValue)+"\n";*/
//                }

                Socketer s = new Socketer(8888);
                while (true) {
                    s.listenForClient();
                    s.sendToClient();
                    s.echoClientData();
                }

//                try {
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                socket.close();
//            } catch (Exception e) {}
//        }
    }
}
