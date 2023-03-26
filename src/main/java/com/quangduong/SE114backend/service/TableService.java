package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.table.TableDTO;
import com.quangduong.SE114backend.dto.table.TableUpdateDTO;

public interface TableService {

    TableDTO createTable(TableDTO dto);
    TableDTO updateTable(TableUpdateDTO dto);
    void deleteTable(long id);
}
