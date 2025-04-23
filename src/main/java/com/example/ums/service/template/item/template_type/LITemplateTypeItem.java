package com.example.ums.service.template.item.template_type;

import com.example.ums.service.template.item.TemplateDisplay;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

public class LITemplateTypeItem extends TemplateTypeItem {

    public LITemplateTypeItem(TemplateDisplay display) {
        super(display);
    }

    @Override
    public boolean supports(String value) {
        return "LI".equals(value);
    }

    @Override
    public void validItem(JsonNode template) {
        if(!template.has("header")) {
            throw new IllegalArgumentException("header is empty.");
        }

        if(!template.has("summary")) {
            throw new IllegalArgumentException("summary is empty.");
        }
    }


    @Override
    public JsonNode validate(JsonNode template) {
        if(!template.has("header")) {
            throw new IllegalArgumentException("header is required. ");
        }

        if (!template.has("summary")) {
            throw new IllegalArgumentException("summary is required.");
        }

        return template;
    }
}
