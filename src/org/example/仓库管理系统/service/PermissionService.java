package org.example.仓库管理系统.service;

import org.example.仓库管理系统.dao.PermissionDao;

public interface PermissionService {
    int yanzhengInquire(PermissionDao permissionDao);
    int  yanzhengInbound(PermissionDao permissionDao);
    int yanzhengOutbound(PermissionDao permissionDao);
    int yanzhengManager(PermissionDao permissionDao);
    int yanzhengFile(PermissionDao permissionDao);
    int yanzhengPerson(PermissionDao permissionDao);

}
