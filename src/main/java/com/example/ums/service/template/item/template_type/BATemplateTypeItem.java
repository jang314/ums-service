package com.example.ums.service.template.item.template_type;

import com.example.ums.service.template.item.*;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BATemplateTypeItem extends TemplateTypeItem {
    public BATemplateTypeItem(TemplateDisplay display) {
        super(display);
    }

    @Override
    public boolean supports(String value) {
        return "BA".equals(value);
    }

    @Override
    public void validItem(JsonNode template) {
        if(!template.has("body")) {
            throw new IllegalArgumentException("body is empty.");
        }
    }
}
