package com.example.ums.service.template.item.template_type;

import com.example.ums.service.template.item.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

public class IMTemplateTypeItem extends TemplateTypeItem {

    public IMTemplateTypeItem(TemplateDisplay display) {
        super(display);
//        super(new ImageItem("image", true,
//                new ButtonItem("buttons", false,
//                        new ButtonItem("quickReplies", false, templateDisplay))));
    }

    @Override
    public boolean supports(String value) {
        return "IM".equals(value);
    }

    @Override
    public void validItem(JsonNode template) {
        if(!template.has("body")) {
            throw new IllegalArgumentException("body is empty.");
        }
    }
}
