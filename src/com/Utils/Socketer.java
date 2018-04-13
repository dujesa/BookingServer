package com.Utils;

import com.Entity.User;
import com.Repository.UserRepository;
import vendor.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Socketer {

     ServerSocket serverSocket   = null;

     User orderer                = null;
     BufferedReader bufferedReader       = null;
     BufferedWriter bufferedWriter       = null;
     JSONObject response                 = new JSONObject();
     String route                        = null;
     JSONObject data                     = null;
     JSONObject user                     = null;

    int serverPort                         = 0;

    InputStream inStreamReader          = null;

    UserRepository userRepo = new UserRepository();

    public Socketer(/*String serverAddress,*/ int serverPort) throws IOException {

        this.serverPort = serverPort;

        serverSocket = new ServerSocket(serverPort);

        //binding created server socket with host and port number

        System.out.println("Server started and listening to the "+ serverPort);






    }
    public void listenForClient(/*String serverAddress, int serverPort*/) throws Exception {

        ArrayList<Object> req = new ArrayList<>();

        Socket clientsocket = this.serverSocket.accept();

        System.out.println("Client connected on server...");

        bufferedReader = new BufferedReader(
                new InputStreamReader(
                        clientsocket.getInputStream()
                )
        );
        bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        clientsocket.getOutputStream()
                )
        );

        DataInputStream din=new DataInputStream(clientsocket.getInputStream());
        DataOutputStream dout=new DataOutputStream(clientsocket.getOutputStream());

            while (true) {

                String messageReceived = null;

                messageReceived=din.readUTF();
                JSONObject request = new JSONObject(messageReceived);

                route = request.getString("route");
                data = request.getJSONObject("data");

                req.clear();
                req.add(route);
                req.add(data);

                response = Router.routeRequest(req);
                dout.writeUTF(response.toString());
                dout.flush();

                System.out.println(response);
            }
        }

    public void sendToClient(JSONObject response) throws IOException {
        //Sending the response back to client



        //bufferedWriter.close();

        this.echoClientData(response);

    }
    //dummy function
    public void echoClientData(JSONObject response) {

        System.out.println(response);
    }

}
