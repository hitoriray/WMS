package org.example.warehouse.service;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.ckDao;

public interface AddckService {
    boolean addck(ckDao ck);

    void addin(boundDao bo);

    void addout(boundDao bo);
}
