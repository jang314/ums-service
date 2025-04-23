package com.example.ums.service.template.item;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class BasicTemplateDisplay extends TemplateDisplay {
    private final String[] validFields;

    public BasicTemplateDisplay(String field) {
        super(field, false);
        this.validFields = new String[0];
    }

    public BasicTemplateDisplay(String field, String[] validFields) {
        super(field,false);
        this.validFields = validFields;
    }

    public BasicTemplateDisplay(String field, boolean required) {
        super(field, required);
        this.validFields = new String[0];
    }

    @Override
    public JsonNode validate(JsonNode template) {
        return template;
    }

    @Override
    public boolean supports(String value) {
        return false;
    }

    @Override
    protected void validItem(JsonNode template) {

    }

    @Override
    public boolean supports(String field, String value) {
        return false;
    }

}
