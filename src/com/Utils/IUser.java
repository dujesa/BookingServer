package com.Utils;

import com.Entity.User;

public interface IUser{
     User addUser();
     boolean resetUserPassword();
     boolean deleteUser();
     User login();
     boolean logout();

}
