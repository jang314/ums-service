package com.example.ums.domain.template.strategy;

import com.example.ums.domain.template.decorated.ITemplate;

public interface TemplateStrategy {
    String parse(String message);

    ITemplate parse(ITemplate template);
}
