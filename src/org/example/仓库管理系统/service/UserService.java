package org.example.仓库管理系统.service;

import org.example.仓库管理系统.dao.UserDao;

public  interface UserService {
    boolean yanzhengadmin(UserDao userDao);
    boolean yanzhengIDnumber(String IDnumber,String name);
    boolean yanzhengOldpwd(String oldpwd);
    boolean yanzhengid(String id,String type);
    boolean yanzhengname(String name);
    String selectID(String name);

}
