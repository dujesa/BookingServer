package com;

import com.Utils.Socketer;

import java.util.ArrayList;


public class Main {



    public static void main(String[] args) throws Exception {

        //kreiranje objekta s iz klase Socketer
        Socketer s = new Socketer(8888);

        ArrayList<Object> clientRequest;
        //Server stalno pokrenut zbog beskonačne petlje
        while (true) {

               s.listenForClient();

        }

    }
}
