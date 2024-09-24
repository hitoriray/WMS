package org.example.仓库管理系统.service;

import org.example.仓库管理系统.dao.ckDao;

public interface RevisionckService {
    boolean Revisionck(ckDao ck);
    boolean revisionsetup(ckDao ck);
    String revisionnumber(ckDao ck);
    String revisionnumber1(ckDao ck);
    void revisionMoreNumber(ckDao ck);
    String  revisionMoreNumber_out(ckDao ck);
    void revisionMoreNumber_outnew(ckDao ck);
}
