package com.example.ums.service.template.item;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class TemplateDisplay {
    private final String field ;
    private final boolean required;

    public TemplateDisplay(String field, boolean required) {
        this.field = field;
        this.required = required;
    }

    public abstract boolean supports(String field, String value);
    public abstract JsonNode validate(JsonNode template) ;
    public abstract boolean supports(String value);
    protected abstract void validItem(JsonNode template);

}