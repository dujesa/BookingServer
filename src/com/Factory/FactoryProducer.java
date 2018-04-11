package com.Factory;

import com.Controller.UserController;

public class FactoryProducer {

    /**
     * Utility class for instantiating objects from given class name
     *
     * @param input
     * @return
     */
    public static AbstractFactory getFactory(String input) {

        if(input.equalsIgnoreCase("UserController"))
            return (AbstractFactory) new UserController();
        else
            return null;
    }
}
