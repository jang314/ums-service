package com.example.ums.service.template.mobile;

import com.example.ums.service.template.item.BasicTemplateDisplay;
import com.example.ums.service.template.item.FileItem;
import com.example.ums.service.template.item.ImageItem;
import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.template_type.TextTemplateItem;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MmsTemplateItem extends MobileTemplateItem {
    private final TemplateDisplay templateDisplay;

    @Autowired
    public MmsTemplateItem() {
        TemplateDisplay templateDisplay = new BasicTemplateDisplay("mms");
        templateDisplay = new TextTemplateItem("subject", false, templateDisplay);
        templateDisplay = new TextTemplateItem("message", true, templateDisplay);
        templateDisplay = new ImageItem("image", true, templateDisplay);
        this.templateDisplay = templateDisplay;
    }

    @Override
    public boolean getServiceType(String serviceType) {
        return "mms".equals(serviceType);
    }

    @Override
    public JsonNode makeTemplate(JsonNode template) {
        return null;
    }
}
