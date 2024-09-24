package org.example.warehouse.service;

import org.example.warehouse.dao.UserDao;

public interface UserService {
    boolean yanzhengadmin(UserDao userDao);

    boolean yanzhengIDnumber(String IDnumber, String name);

    boolean yanzhengOldpwd(String oldpwd);

    boolean yanzhengid(String id, String type);

    boolean yanzhengname(String name);

    String selectID(String name);

}
