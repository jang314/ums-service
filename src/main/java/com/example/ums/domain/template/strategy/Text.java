package com.example.ums.domain.template.strategy;

import com.example.ums.domain.template.decorated.ITemplate;

public class Text implements TemplateStrategy {
    private final String message;

    public Text(String message) {
        this.message = message;
    }


    @Override
    public String parse(String message) {
        return null;
    }

    @Override
    public ITemplate parse(ITemplate template) {
        return null;
    }
}
