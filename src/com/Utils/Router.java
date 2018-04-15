package com.Utils;

import com.Controller.BookingController;
import com.Controller.RoomController;
import com.Controller.UserController;
import vendor.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Util class for routing clients request to different controllers in server
 */
public class Router {

    public static JSONObject routeRequest(/*String routeAndAction, JSONObject data*/ArrayList<Object> routeAndAction) throws SQLException, ClassNotFoundException {

        String route, action;
        JSONObject data;
        JSONObject response = new JSONObject();

//        route = (String) routeAndAction.get(1);

        String[] params = routeAndAction.get(0).toString().split("/");
        data = (JSONObject) routeAndAction.get(1);
        route = params[0];
        action = params[1];

        if(route != null) {
            if (route.equalsIgnoreCase("BookingController")) {

                BookingController controller = new BookingController();

                switch (action) {
                    case "roomBooking":
                        response.put("Response", controller.roomBooking(data));
                        break;
                    case "viewBookings":
                        response.put("Response", controller.viewBookings());
                        break;
                    case "viewSingleBooking":
                        response.put("Response", controller.viewSingleBooking(data));
                        break;
                    case "updateBooking":
                        response.put("Response", controller.updateBooking(data));
                        break;
                    case "deleteBooking":
                        response.put("Response", controller.deleteBooking(data));
                        break;
                    default:
                        response.put("Response", "Route invalid");
                        break;
                }

            } else if (route.equalsIgnoreCase("RoomController")) {

                RoomController controller = new RoomController();

                switch (action) {
                    case "viewSingleRoom":
                        response.put("Response", controller.viewSingleRoom(data));
                        break;
                    case "viewAvailableRooms":
                        response.put("Response", controller.viewAvailableRooms(data));
                        break;
                    default:
                        response.put("Response", "Route invalid");
                        break;
                }

            } else if (route.equalsIgnoreCase("UserController")) {

                UserController controller = new UserController();

                switch (action) {
                    case "addUser":
                        response.put("Response", controller.addUser(data));
                        break;
                    case "resetUserPassword":
                        response.put("Response", controller.resetUserPassword(data));
                        break;
                    case "deleteUser":
                        response.put("Response", controller.deleteUser(data));
                        break;
                    case "login":
                        response.put("Response", controller.login(data));
                        break;
                    case "logout":
                        response.put("Response", controller.logout());
                        break;
                    default:
                        response.put("Response", "Route invalid");
                        break;
                }


            }
            System.out.println(response);
            DBConnecter.closeDatabaseConnection();
            return response;
        }

            return null;


    }

}

