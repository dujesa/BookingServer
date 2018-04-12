package com.Utils;

import com.Entity.User;
import com.Repository.UserRepository;
import vendor.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Socketer {

    private ServerSocket serverSocket   = null;
    private Socket socket               = null;
    private User orderer                = null;
    //private String response           = null;
    JSONObject response                 = new JSONObject();
    String route                        = null;
    JSONObject data                     = null;
    JSONObject user                     = null;
    private String messageReceived      = null;
    private int serverPort              = 0;
    UserRepository userRepo = new UserRepository();

    public  Socketer(/*String serverAddress,*/ int serverPort) throws IOException {

        serverSocket = new ServerSocket();
        this.serverPort = serverPort;
        //binding created server socket with host and port number
        serverSocket.bind(new InetSocketAddress("10.22.33.102", serverPort));
        System.out.println("Server started and listening to the "+serverPort);

    }
    public ArrayList<Object> listenForClient(/*String serverAddress, int serverPort*/) throws IOException, SQLException, ClassNotFoundException {


        socket = serverSocket.accept();
        System.out.println("Client connected on port "+this.serverPort);
        InputStream inStream = socket.getInputStream();
        InputStreamReader inStreamReader = new InputStreamReader(inStream);
        BufferedReader buffReader = new BufferedReader(inStreamReader);
        messageReceived = buffReader.readLine();
//
//      System.out.println("Message received from client is " + messageReceived);

//      Executing operation on received message

//      Gson gson = new Gson();
//      response = jobj.get("User").toString();
//      System.out.println(messageReceived);

        JSONObject request = new JSONObject(messageReceived);

//      user = request.getJSONObject("User");

//      user = request.getJSONObject("ResetPassword");


//      request = request.getString("User");
//      orderer = gson.fromJson(request, User.class);
//      response = new JSONObject();

        route = request.getString("route");
        data = request.getJSONObject("data");
        ArrayList<Object> req = new ArrayList<Object>();

//----------------------------------------------------
//        response = new JSONObject();
//        response.put(
//                "Conformation",
//                userRepo.resetUserPassword(user)
//        );
//----------------------------------------------------

        req.add(route);
        req.add(data);


//            response.put("User", user
//                    .getJSONObject("User")
//                    .getString("name")
//            );


        return req;
    }

    public void sendToClient(JSONObject response) throws IOException {
        //Sending the response back to client
        OutputStream outStream = socket.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream);
        BufferedWriter buffWriter = new BufferedWriter(outStreamWriter);

        buffWriter.write(response.toString());
        buffWriter.flush();
        buffWriter.close();

        this.echoClientData(response);

    }
    //dummy function
    public void echoClientData(JSONObject response) {

        System.out.println(response);
    }
}
