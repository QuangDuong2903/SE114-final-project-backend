package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.board.BoardDTO;
import com.quangduong.SE114backend.dto.board.BoardUpdateDTO;

public interface BoardService {

    BoardDTO createBoard(BoardDTO dto);
    BoardDTO updateBoard(BoardUpdateDTO dto);
    void deleteBoardById(long id);

}
