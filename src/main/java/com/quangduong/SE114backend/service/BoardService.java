package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.board.BoardDTO;
import com.quangduong.SE114backend.dto.board.BoardDetailsDTO;
import com.quangduong.SE114backend.dto.board.BoardUpdateDTO;
import com.quangduong.SE114backend.dto.board.chart.ChartDTO;

public interface BoardService {

    BoardDetailsDTO getBoardDetails(long id);
    ChartDTO getChartData(long id);
    BoardDTO createBoard(BoardDTO dto);
    BoardDetailsDTO updateBoard(BoardUpdateDTO dto);
    void deleteBoardById(long id);

}
