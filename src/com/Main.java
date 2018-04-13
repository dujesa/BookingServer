package com;

import com.Utils.Router;
import com.Utils.Socketer;

import java.net.Socket;
import java.util.ArrayList;


public class Main {

    private static Socket socket;

    public static void main(String[] args) throws Exception {


    //        ArrayList<Object> clientRequest;

        Socketer s = new Socketer(8888);
        //Server is running always due to infinite loop
        while (true) {
//            try {
                ArrayList<Object> clientRequest;
                clientRequest = s.listenForClient();

                System.out.println(clientRequest);

                s.sendToClient(
                        Router.routeRequest(clientRequest)
                );

//            } catch (Exception e) {
//                System.out.println("Client disconnected...");
//            }
        }
//        System.out.println("Server ended with work...");
    }
}
