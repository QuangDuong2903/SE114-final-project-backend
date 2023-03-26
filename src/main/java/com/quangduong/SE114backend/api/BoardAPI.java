package com.quangduong.SE114backend.api;

import com.quangduong.SE114backend.dto.board.BoardDTO;
import com.quangduong.SE114backend.dto.board.BoardUpdateDTO;
import com.quangduong.SE114backend.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("board")
public class BoardAPI {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(@RequestBody @Valid BoardDTO dto) {
        return new ResponseEntity<>(boardService.createBoard(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable("id") long id, @RequestBody BoardUpdateDTO dto) {
        dto.setId(id);
        return new ResponseEntity<>(boardService.updateBoard(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> createBoard(@PathVariable("id") long id) {
        boardService.deleteBoardById(id);
        return ResponseEntity.ok().body(null);
    }

}
