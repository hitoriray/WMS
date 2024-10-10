package org.example.warehouse.service;

import org.example.warehouse.dao.warehouseDao;

public interface RevisionItemService {
    boolean revisionItem(warehouseDao warehouseDao);

    boolean revisionSetup(warehouseDao warehouseDao);

    String revisionNumber(warehouseDao warehouseDao);

    String revisionNumber1(warehouseDao warehouseDao);

    void revisionMoreNumber(warehouseDao warehouseDao);

    String revisionMoreNumberOut(warehouseDao warehouseDao);

    void revisionMoreNumberOut1(warehouseDao warehouseDao);
}
