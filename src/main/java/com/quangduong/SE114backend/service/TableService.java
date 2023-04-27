package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.table.TableDTO;
import com.quangduong.SE114backend.dto.table.TableDetailsDTO;
import com.quangduong.SE114backend.dto.table.TableUpdateDTO;

public interface TableService {

    TableDetailsDTO createTable(TableDTO dto);
    TableDetailsDTO updateTable(TableUpdateDTO dto);
    void deleteTable(long id);
}
