package org.example.warehouse.service;

import org.example.warehouse.dao.ckDao;

public interface RevisionckService {
    boolean Revisionck(ckDao ck);

    boolean revisionsetup(ckDao ck);

    String revisionnumber(ckDao ck);

    String revisionnumber1(ckDao ck);

    void revisionMoreNumber(ckDao ck);

    String revisionMoreNumber_out(ckDao ck);

    void revisionMoreNumber_outnew(ckDao ck);
}
