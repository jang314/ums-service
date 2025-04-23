package com.example.ums.service.template.item.button;

import com.example.ums.service.template.item.ButtonItem;
import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.TemplateItemDecorator;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeblinkButton extends TemplateItemDecorator {
    public WeblinkButton(String field, boolean required, TemplateDisplay templateDisplay) {
        super(field, required, templateDisplay);
    }


    @Override
    public boolean supports(String value) {
        return "WL".equals(value);
    }

    @Override
    protected void validItem(JsonNode template) {
        // 버튼
        if(!template.has("linkMobile")) {
            throw new IllegalArgumentException("linkMobile is empty.");
        }

        if(!template.has("linkPc")) {
            throw new IllegalArgumentException("linkPc is empty.");
        }

        // 바로가기연결
//        link
    }
}
