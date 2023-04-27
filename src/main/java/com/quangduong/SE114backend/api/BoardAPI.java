package com.quangduong.SE114backend.api;

import com.quangduong.SE114backend.dto.board.BoardDTO;
import com.quangduong.SE114backend.dto.board.BoardDetailsDTO;
import com.quangduong.SE114backend.dto.board.BoardUpdateDTO;
import com.quangduong.SE114backend.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("boards")
public class BoardAPI {

    @Autowired
    private BoardService boardService;

    @GetMapping("{id}")
    public ResponseEntity<BoardDetailsDTO> getBoardDetails(@PathVariable("id") long id) {
        return ResponseEntity.ok(boardService.getBoardDetails(id));
    }

    @PostMapping
    public ResponseEntity<CreateBoardResponse> createBoard(@RequestBody @Valid BoardDTO dto) {
        BoardDTO boardDTO = boardService.createBoard(dto);
        return new ResponseEntity<>(new CreateBoardResponse(boardDTO.getId(), boardDTO.getName()), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BoardDetailsDTO> updateBoard(@PathVariable("id") long id, @RequestBody BoardUpdateDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(boardService.updateBoard(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("id") long id) {
        boardService.deleteBoardById(id);
        return ResponseEntity.ok().body(null);
    }

    record CreateBoardResponse(Long id, String name) {}

}
