package com.Utils;

import json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Socketer {

    private ServerSocket serverSocket   = null;
    private Socket socket               = null;
    //private User orderer              = null;
    //private String response           = null;
    JSONObject response                 = null;
    private String messageReceived      = null;
    private int serverPort;

    public  Socketer(/*String serverAddress,*/ int serverPort) throws IOException {
        serverSocket = new ServerSocket();
        this.serverPort = serverPort;
        //binding created server socket with host and port number
        serverSocket.bind(new InetSocketAddress("10.22.33.102", serverPort));
        System.out.println("Server started and listening to the "+serverPort);
    }
    public void listenForClient(/*String serverAddress, int serverPort*/) throws IOException {

        //Server is running always due to infinite loop
//        while(true) {

//            try {
                //Reading message sent by client
                socket = serverSocket.accept();
                System.out.println("Client connected on port "+this.serverPort);
                InputStream inStream = socket.getInputStream();
                InputStreamReader inStreamReader = new InputStreamReader(inStream);
                BufferedReader buffReader = new BufferedReader(inStreamReader);
                messageReceived = buffReader.readLine();
                //System.out.println("Message received from client is " + messageReceived);


                //Executing operation on received message

                //Gson gson = new Gson();
                //Error in input format - messageReceived
                //User orderer = new User(messageReceived);


//                orderer = gson.fromJson(messageReceived, User.class);
                //JsonObject jobj = gson.fromJson(messageReceived, JsonObject.class);
//
//                response = jobj.get("User").toString();
            System.out.println(messageReceived);
            JSONObject user = new JSONObject(messageReceived);
            response = new JSONObject();


            response.put("Name", user
                    .getJSONObject("User")
                    .getString("name")
            );
            return;


                //problem is that this object of Gson class wont get null properties
                //System.out.println(orderer.getMail());


//                return gson.toJson(orderer);


//            } catch (Exception e) {
//                    /*int receivedNumber = Integer.parseInt(messageReceived);
//                    int returnValue = receivedNumber * 2;
//                    response = String.valueOf(returnValue)+"\n";*/
//            }
//        }

    }

    public void sendToClient() throws IOException {
        //Sending the response back to client
        OutputStream outStream = socket.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream);
        BufferedWriter buffWriter = new BufferedWriter(outStreamWriter);

        buffWriter.write(response.toString());
        buffWriter.flush();
        buffWriter.close();

    }
    //dummy function
    public void echoClientData() {

        System.out.println(this.response);
    }
}
