package org.example.warehouse.service;

import org.example.warehouse.dao.UserTotalDao;

public interface RevisionService {
    boolean yangzhenRevision(UserTotalDao userTotalDao);

    void updatepwd(String oldpwd, String pwd);
}
