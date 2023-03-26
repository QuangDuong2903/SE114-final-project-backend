package com.quangduong.SE114backend.service.impl;

import com.quangduong.SE114backend.dto.table.TableDTO;
import com.quangduong.SE114backend.dto.table.TableUpdateDTO;
import com.quangduong.SE114backend.entity.TableEntity;
import com.quangduong.SE114backend.exception.NoPermissionException;
import com.quangduong.SE114backend.exception.ResourceNotFoundException;
import com.quangduong.SE114backend.mapper.TableMapper;
import com.quangduong.SE114backend.repository.TableRepository;
import com.quangduong.SE114backend.service.TableService;
import com.quangduong.SE114backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    @Transactional
    public TableDTO createTable(TableDTO dto) {
        return tableMapper.toDTO(tableRepository.save(tableMapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public TableDTO updateTable(TableUpdateDTO dto) {
        long id = dto.getId();
        TableEntity entity = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found table with id: " + id));
        if (!entity.getCreatedBy().equals(securityUtils.getCurrentUser().getEmail()))
            throw new NoPermissionException("Update table with id: " + id + " not allowed");
        return tableMapper.toDTO(tableRepository.save(tableMapper.toEntity(dto, entity)));
    }

    @Override
    @Transactional
    public void deleteTable(long id) {
        TableEntity entity = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found table with id: " + id));
        if (!entity.getCreatedBy().equals(securityUtils.getCurrentUser().getEmail()))
            throw new NoPermissionException("Update table with id: " + id + " not allowed");
        tableRepository.deleteById(id);
    }
}
