package com;

import com.Utils.Socketer;

import java.util.ArrayList;


public class Main {



    public static void main(String[] args) throws Exception {


        Socketer s = new Socketer(8888);

        ArrayList<Object> clientRequest;
        //Server is running always due to infinite loop
        while (true) {

               s.listenForClient();

        }

    }
}
