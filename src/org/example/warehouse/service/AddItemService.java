package org.example.warehouse.service;

import org.example.warehouse.dao.boundDao;
import org.example.warehouse.dao.warehouseDao;

public interface AddItemService {
    boolean addItem(warehouseDao warehouseDao);

    void addInbound(boundDao boundDao);

    void addOutbound(boundDao boundDao);
}
