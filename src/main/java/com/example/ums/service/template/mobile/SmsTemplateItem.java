package com.example.ums.service.template.mobile;

import com.example.ums.dto.TemplateDto;
import com.example.ums.service.template.TemplateItemComponent;
import com.example.ums.service.template.item.BasicTemplateDisplay;
import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.template_type.TextTemplateItem;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsTemplateItem extends MobileTemplateItem {
    private final TemplateDisplay templateDisplay;

    @Autowired
    public SmsTemplateItem() {
        TemplateDisplay display = new BasicTemplateDisplay("sms");
        display = new TextTemplateItem("message", true, display);
        templateDisplay = display;
    }

    @Override
    public boolean getServiceType(String serviceType) {
        return "sms".equals(serviceType);
    }

    @Override
    public JsonNode makeTemplate(JsonNode template) {
        return null;
    }
}
