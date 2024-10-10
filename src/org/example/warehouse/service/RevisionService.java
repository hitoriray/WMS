package org.example.warehouse.service;

import org.example.warehouse.dao.UserTotalDao;

public interface RevisionService {
    boolean verifyRevision(UserTotalDao userTotalDao);

    void modifyPwd(String oldPwd, String newPwd);
}
