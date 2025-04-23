package com.example.ums.service.template.item.template_type;

import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.TemplateItemDecorator;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextTemplateItem extends TemplateItemDecorator {
    public TextTemplateItem(String field, boolean required, TemplateDisplay templateDisplay) {
        super(field, required, templateDisplay);
    }

    @Override
    public boolean supports(String value) {
        return "TEXT".equals(value);
    }

    @Override
    protected void validItem(JsonNode template) {
        if(!template.has("title")) {
            throw new IllegalArgumentException("title is required.");
        }
    }
}
