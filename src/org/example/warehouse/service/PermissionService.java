package org.example.warehouse.service;

import org.example.warehouse.dao.PermissionDao;

public interface PermissionService {
    int yanzhengInquire(PermissionDao permissionDao);

    int yanzhengInbound(PermissionDao permissionDao);

    int yanzhengOutbound(PermissionDao permissionDao);

    int yanzhengManager(PermissionDao permissionDao);

    int yanzhengFile(PermissionDao permissionDao);

    int yanzhengPerson(PermissionDao permissionDao);
}
