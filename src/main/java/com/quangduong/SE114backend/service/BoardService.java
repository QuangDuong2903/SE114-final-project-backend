package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.board.BoardDTO;
import com.quangduong.SE114backend.dto.board.BoardDetailsDTO;
import com.quangduong.SE114backend.dto.board.BoardUpdateDTO;

public interface BoardService {

    BoardDetailsDTO getBoardDetails(long id);
    BoardDTO createBoard(BoardDTO dto);
    BoardDTO updateBoard(BoardUpdateDTO dto);
    void deleteBoardById(long id);

}
