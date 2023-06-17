package com.quangduong.SE114backend.api;

import com.quangduong.SE114backend.dto.label.LabelDTO;
import com.quangduong.SE114backend.dto.label.LabelUpdateDTO;
import com.quangduong.SE114backend.service.LabelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("labels")
public class LabelAPI {

    @Autowired
    private LabelService labelService;

    @PostMapping
    public ResponseEntity<LabelDTO> createLabel(@RequestBody @Valid LabelDTO dto) {
        return new ResponseEntity<>(labelService.createLabel(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<LabelDTO> updateLabel(@PathVariable("id") long id, @RequestBody LabelUpdateDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(labelService.updateLabel(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> createLabel(@PathVariable("id") long id) {
        labelService.deleteLabel(id);
        return ResponseEntity.ok().build();
    }

}
