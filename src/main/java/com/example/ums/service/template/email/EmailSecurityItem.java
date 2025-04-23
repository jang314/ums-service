package com.example.ums.service.template.email;


import com.example.ums.service.template.TemplateItemComponent;
import com.example.ums.service.template.item.BasicTemplateDisplay;
import com.example.ums.service.template.item.FileItem;
import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.template_type.TextTemplateItem;
import com.example.ums.validator.ArrayValidator;
import com.example.ums.validator.ItemValidator;
import com.example.ums.validator.TemplateItem;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class EmailSecurityItem extends EmailTemplateItem {
    private final TemplateDisplay templateDisplay;

    @Autowired
    public EmailSecurityItem() {
        TemplateDisplay templateDisplay = new BasicTemplateDisplay("email");
        templateDisplay = new TextTemplateItem("subject", false, templateDisplay);
        templateDisplay = new FileItem("cover", true, templateDisplay);
        templateDisplay = new FileItem("body", false, templateDisplay);
        templateDisplay = new FileItem("footer", false, templateDisplay);
        templateDisplay = new FileItem("files", true, templateDisplay);

        this.templateDisplay = templateDisplay;
    }

    @Override
    public boolean getServiceType(String serviceType) {
        return "security".equals(serviceType);
    }

    @Override
    public JsonNode makeTemplate(JsonNode template) {
        return null;
    }
}
