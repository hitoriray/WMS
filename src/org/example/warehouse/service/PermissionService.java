package org.example.warehouse.service;

import org.example.warehouse.dao.PermissionDao;

public interface PermissionService {
    int verifyPermission(PermissionDao permissionDao);

    int verifyInbound(PermissionDao permissionDao);

    int verifyOutbound(PermissionDao permissionDao);

    int verifyManager(PermissionDao permissionDao);

    int verifyFile(PermissionDao permissionDao);

    int verifyPerson(PermissionDao permissionDao);
}
