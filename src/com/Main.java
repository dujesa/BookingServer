package com;

import com.Utils.Socketer;

import java.util.ArrayList;


public class Main {



    public static void main(String[] args) throws Exception {


    //        ArrayList<Object> clientRequest;

        Socketer s = new Socketer(8888);

        ArrayList<Object> clientRequest;
        //Server is running always due to infinite loop
        while (true) {
//            try {

               s.listenForClient();

//                System.out.println(clientRequest);

//                s.sendToClient(
//
//                );

//            } catch (Exception e) {
//                System.out.println("Client disconnected...");
//            }
        }
//        System.out.println("Server ended with work...");
    }
}
