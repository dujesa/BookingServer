package com;

import com.Utils.Socketer;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;


public class Main {

    private static Socket socket;

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {


                Socketer s = new Socketer(8888);
                //Server is running always due to infinite loop
                while (true) {
                    s.listenForClient();
                    s.sendToClient();
                    s.echoClientData();
                }

    }
}
