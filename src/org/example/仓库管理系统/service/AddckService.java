package org.example.仓库管理系统.service;

import org.example.仓库管理系统.dao.boundDao;
import org.example.仓库管理系统.dao.ckDao;

public interface AddckService {
     boolean addck(ckDao ck);
     void addin(boundDao bo);
     void addout(boundDao bo);
}
