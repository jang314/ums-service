package com.example.ums.service.template.item.template_type;

import com.example.ums.service.template.item.TemplateDisplay;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

public class BITemplateTypeItem extends TemplateTypeItem {

    public BITemplateTypeItem(TemplateDisplay display) {
        super(display);
    }

    @Override
    public boolean supports(String value) {
        return "BI".equals(value);
    }

    @Override
    public void validItem(JsonNode template) {
        if(!template.has("header")) {
            throw new IllegalArgumentException("header is empty.");
        }

        if (!template.has("summary")) {
            throw new IllegalArgumentException("summary is empty.");
        }
    }

}
