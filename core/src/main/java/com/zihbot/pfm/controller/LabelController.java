package com.zihbot.pfm.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.zihbot.pfm.dao.Label;
import com.zihbot.pfm.service.LabelService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {
	private final LabelService labelService;

	@GetMapping()
	public List<String> listLabels() {
		List<Label> labels = labelService.listLabels();
        return labels.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
	}

    private String convertToDto(final Label input) {
        return input.getName();
    }
}
