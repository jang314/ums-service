package com.example.ums.service.template.item.template_type;

import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.TemplateItemDecorator;
import com.fasterxml.jackson.databind.JsonNode;

public abstract class TemplateTypeItem extends TemplateItemDecorator {

    public TemplateTypeItem(TemplateDisplay templateDisplay) {
        super("templateType", true, templateDisplay);
    }


}
