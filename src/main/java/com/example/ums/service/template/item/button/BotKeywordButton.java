package com.example.ums.service.template.item.button;

import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.TemplateItemDecorator;
import com.fasterxml.jackson.databind.JsonNode;

public class BotKeywordButton extends TemplateItemDecorator {
    public BotKeywordButton(String field, boolean required, TemplateDisplay templateDisplay) {
        super(field, required, templateDisplay);
    }

    @Override
    public boolean supports(String value) {
        return "BK".equals(value);
    }

    @Override
    protected void validItem(JsonNode template) {
        if(!template.has("keyword")) {
            throw new IllegalArgumentException("keyword is required.");
        }
    }
}
