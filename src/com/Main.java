package com;

import com.Utils.Router;
import com.Utils.Socketer;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {

    private static Socket socket;

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {


//        ArrayList<Object> clientRequest;
        Socketer s = new Socketer(8888);
        //Server is running always due to infinite loop
        while (true) {
            ArrayList<Object> clientRequest;
            clientRequest = s.listenForClient();

            System.out.println(clientRequest);

            s.sendToClient(
            Router.routeRequest(clientRequest)
            );
            //s.echoClientData(response);
        }

    }
}
