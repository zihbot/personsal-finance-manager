package com.zihbot.pfm.service;

import java.util.List;

import com.zihbot.pfm.dao.Label;
import com.zihbot.pfm.repository.LabelRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;

	public List<Label> listLabels() {
        List<Label> labels = labelRepository.findAll();
        return labels;
    }

    public Label insertLabel(Label label) {
        label.setId(null);
        if (label.getName() == null) {
            throw new NullPointerException("No name for label");
        }
        return labelRepository.save(label);
    }
}
