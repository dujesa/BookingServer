package com.Utils;

import vendor.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Utility klasa za routanje requestova sa klijenta
 */
public class Socketer {

     ServerSocket serverSocket           = null;
     JSONObject response                 = new JSONObject();
     String route                        = null;
     JSONObject data                     = null;
     int serverPort                      = 0;


    /**
     * Konstruktor za instanciranje objekta iz Socketer klase
     */
    public Socketer(/*String serverAddress,*/ int serverPort) throws IOException {

        this.serverPort = serverPort;

        serverSocket = new ServerSocket(serverPort);

        //binding created server socket with host and port number

        System.out.println("Server started and listening to the "+ serverPort);


    }

    /**
     * Metoda za čekanje klijenta, dok se ne spoji na određen port i IP adresu određenu na serveru
     *
     * @throws Exception
     */
    public void listenForClient(/*String serverAddress, int serverPort*/) throws Exception {

        ArrayList<Object> req = new ArrayList<>();

        Socket clientsocket = this.serverSocket.accept();

        System.out.println("Client connected on server...");

        DataInputStream din = new DataInputStream(clientsocket.getInputStream());
        DataOutputStream dout = new DataOutputStream(clientsocket.getOutputStream());

        try {
            while (true) {

                String messageReceived = null;


                messageReceived = din.readUTF();
                JSONObject request = new JSONObject(messageReceived);


                System.out.println(request.toString());
                route = request.getString("route");
                data = request.getJSONObject("data");


                req.clear();
                req.add(route);
                req.add(data);

                response = Router.routeRequest(req);


                dout.writeUTF(response.toString());
                dout.flush();

            }
        }catch (Exception e){}
    }

}
