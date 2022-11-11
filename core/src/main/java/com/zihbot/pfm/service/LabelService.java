package com.zihbot.pfm.service;

import java.util.List;

import com.zihbot.pfm.dao.Label;
import com.zihbot.pfm.repository.LabelRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabelService {
    private final UserService user;
    private final LabelRepository labelRepository;

	public List<Label> listLabels() {
        return labelRepository.findAllByUser(user.username());
    }

    public Label createOrGetLabel(String name) {
        Label nameLabel = labelRepository.getByUserAndName(user.username(), name);
        if (nameLabel != null) {
            return nameLabel;
        }
        Label label = new Label();
        label.setName(name);
        return insertLabel(label);
    }

    public Label insertLabel(Label label) {
        label.setId(null);
        if (label.getName() == null) {
            throw new NullPointerException("No name for label");
        }
        return labelRepository.save(label);
    }

	public void deleteByUser(String user) {
        labelRepository.deleteAllByUser(user);
    }
}
