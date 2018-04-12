package com.Utils;

import com.Entity.User;
import com.Repository.UserRepository;
import com.google.gson.Gson;
import vendor.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Socketer {

    private ServerSocket serverSocket   = null;
    private Socket socket               = null;
    private User orderer              = null;
    //private String response           = null;
    JSONObject response                 = null;
    JSONObject user                 = null;
    private String messageReceived      = null;
    private int serverPort;
    UserRepository userRepo = new UserRepository();

    public  Socketer(/*String serverAddress,*/ int serverPort) throws IOException {
        serverSocket = new ServerSocket();
        this.serverPort = serverPort;
        //binding created server socket with host and port number
        serverSocket.bind(new InetSocketAddress("10.22.33.102", serverPort));
        System.out.println("Server started and listening to the "+serverPort);
    }
    public void listenForClient(/*String serverAddress, int serverPort*/) throws IOException, SQLException, ClassNotFoundException {



                socket = serverSocket.accept();
                System.out.println("Client connected on port "+this.serverPort);
                InputStream inStream = socket.getInputStream();
                InputStreamReader inStreamReader = new InputStreamReader(inStream);
                BufferedReader buffReader = new BufferedReader(inStreamReader);
                messageReceived = buffReader.readLine();
                //System.out.println("Message received from client is " + messageReceived);


                //Executing operation on received message

                Gson gson = new Gson();
                //Error in input format - messageReceived
//                User orderer = new User(messageReceived);


//                orderer = gson.fromJson(messageReceived, User.class);
                //JsonObject jobj = gson.fromJson(messageReceived, JsonObject.class);
//
//                response = jobj.get("User").toString();
            //System.out.println(messageReceived);
            JSONObject request = new JSONObject(messageReceived);
            user = request.getJSONObject("User");
//            orderer = new User(
//                    request.getString("name"),
//                    request.getString("lastName"),
//                    request.getString("username"),
//                    request.getString("password"),
//                    request.getString("officeNumber"),
//                    request.getString("userPhone"),
//                    request.getString("mail"),
//                    request.getString("role")
//                    );

//            request = request.getString("User");
//            orderer = gson.fromJson(request, User.class);
//            response = new JSONObject();


            response = new JSONObject();
            response.put(
                    "Conformation",
                    userRepo.addUser(user)
        );
//            response.put("User", user
//                    .getJSONObject("User")
//                    .getString("name")
//            );
//        response = request.getJSONObject("User");
      //  System.out.println(response);

        //           response.put("Conformation", request
//                    .getJSONObject("User")
//            );
            return;


                //problem is that this object of Gson class wont get null properties
                //System.out.println(orderer.getMail());


//                return gson.toJson(orderer);


//            } catch (Exception e) {
//                    /*int receivedNumber = Integer.parseInt(messageReceived);
//                    int returnValue = receivedNumber * 2;
//                    response = String.valueOf(returnValue)+"\n";*/
//            }
//        }

    }

    public void sendToClient() throws IOException {
        //Sending the response back to client
        OutputStream outStream = socket.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream);
        BufferedWriter buffWriter = new BufferedWriter(outStreamWriter);

        buffWriter.write(response.toString());
        buffWriter.flush();
        buffWriter.close();

    }
    //dummy function
    public void echoClientData() {

        System.out.println(this.response);
    }
}
