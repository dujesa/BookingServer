package com.Utils;

import com.Entity.User;
import com.Repository.UserRepository;
import vendor.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Socketer {

    private ServerSocket serverSocket   = null;
    private Socket socket               = null;
    private User orderer                = null;
    BufferedReader bufferedReader       = null;
    BufferedWriter bufferedWriter       = null;
    JSONObject response                 = new JSONObject();
    String route                        = null;
    JSONObject data                     = null;
    JSONObject user                     = null;
    private String messageReceived      = null;
    private int serverPort              = 0;
    InputStream inStreamReader          = null;

    UserRepository userRepo = new UserRepository();

    public Socketer(/*String serverAddress,*/ int serverPort) throws IOException {

        serverSocket = new ServerSocket();
        this.serverPort = serverPort;
        //binding created server socket with host and port number
        serverSocket.bind(new InetSocketAddress("10.22.33.102", serverPort));
        System.out.println("Server started and listening to the "+serverPort);

        socket = serverSocket.accept();

        System.out.println("Client connected on server...");

        bufferedReader = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()
                )
        );
        bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream()
                )
        );

    }
    public ArrayList<Object> listenForClient(/*String serverAddress, int serverPort*/) throws Exception {



            this.messageReceived = bufferedReader.readLine();

            JSONObject request = new JSONObject(messageReceived);

            route = request.getString("route");
            data = request.getJSONObject("data");
            ArrayList<Object> req = new ArrayList<>();

            req.add(route);
            req.add(data);




        return req;
    }

    public void sendToClient(JSONObject response) throws IOException {
        //Sending the response back to client


        bufferedWriter.write(response.toString());
        bufferedWriter.flush();
        //bufferedWriter.close();

        this.echoClientData(response);

    }
    //dummy function
    public void echoClientData(JSONObject response) {

        System.out.println(response);
    }

}
