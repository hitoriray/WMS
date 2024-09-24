package org.example.仓库管理系统.service;

import org.example.仓库管理系统.dao.UserTotalDao;

public interface RevisionService {
    boolean yangzhenRevision(UserTotalDao userTotalDao);
    void updatepwd(String oldpwd,String pwd);
}
