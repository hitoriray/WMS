package org.example.warehouse.service;

import org.example.warehouse.dao.UserDao;

public interface UserService {
    boolean verifyAdmin(UserDao userDao);

    boolean verifyIDnumberByName(String IDnumber, String name);

    boolean verifyPwd(String pwd);

    boolean verifyId(String id, String type);

    boolean verifyName(String name);

    String selectID(String name);

}
