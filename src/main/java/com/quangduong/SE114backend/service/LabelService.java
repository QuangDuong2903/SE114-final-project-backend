package com.quangduong.SE114backend.service;

import com.quangduong.SE114backend.dto.label.LabelDTO;
import com.quangduong.SE114backend.dto.label.LabelUpdateDTO;

public interface LabelService {

    LabelDTO createLabel(LabelDTO dto);
    LabelDTO updateLabel(LabelUpdateDTO dto);
    void deleteLabel(long id);
}
