package com.example.ums.validator;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class TemplateItemDecorator implements TemplateItem {
    private TemplateItem validator;

    public TemplateItemDecorator(TemplateItem validator) {
        this.validator = validator;
    }

    @Override
    public JsonNode validate(JsonNode items) {
        log.info("itemAdditionalValidator : " + validator.getClass().getSimpleName());
        return validator.validate(items);
    }
}
