package com.quangduong.SE114backend.service.impl;

import com.quangduong.SE114backend.dto.board.BoardDTO;
import com.quangduong.SE114backend.dto.board.BoardUpdateDTO;
import com.quangduong.SE114backend.exception.NoPermissionException;
import com.quangduong.SE114backend.exception.ResourceNotFoundException;
import com.quangduong.SE114backend.mapper.BoardMapper;
import com.quangduong.SE114backend.repository.sql.BoardRepository;
import com.quangduong.SE114backend.service.BoardService;
import com.quangduong.SE114backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    @Transactional
    public BoardDTO createBoard(BoardDTO dto) {
        return boardMapper.toDTO(boardRepository.save(boardMapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public BoardDTO updateBoard(BoardUpdateDTO dto) {
        long id = dto.getId();
        if(securityUtils.getCurrentUserId() != boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id)).getId())
            throw new NoPermissionException("Update board with id: " + id + " not allowed");
        return boardMapper.toDTO(
                boardRepository.save(boardMapper.toEntity(dto, boardRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + dto.getId()))
                ))
        );
    }

    @Override
    @Transactional
    public void deleteBoardById(long id) {
        if(securityUtils.getCurrentUserId() != boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id)).getId())
            throw new NoPermissionException("Delete board with id: " + id + " not allowed");
        boardRepository.deleteById(id);
    }
}
