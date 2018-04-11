package com.Factory;

import com.Entity.User;
import com.Utils.IUser;

interface AbstractFactory extends IUser {

    User addUser();
    boolean resetUserPassword();
    boolean deleteUser();
    User login();
    boolean logout();


}
