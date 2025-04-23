package com.example.ums.service.template.item.button;

import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.TemplateItemDecorator;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppLinkButton extends TemplateItemDecorator {

    public AppLinkButton(String field, boolean required, TemplateDisplay templateDisplay) {
        super(field, required, templateDisplay);
    }

    @Override
    public boolean supports(String value) {
        return false;
    }

    @Override
    protected void validItem(JsonNode template) {
        if(!template.has("linkIos")) {
            throw new IllegalArgumentException("linkMobile is empty.");
        }

        if(!template.has("linkAndroid")) {
            throw new IllegalArgumentException("linkPc is empty.");
        }
    }
}
