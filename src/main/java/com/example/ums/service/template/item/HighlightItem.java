package com.example.ums.service.template.item;

import com.example.ums.service.template.item.template_type.TextTemplateItem;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class HighlightItem extends TemplateItemDecorator {
    private Map<String, TemplateDisplay> templateDisplayMap = new HashMap<>();
    public HighlightItem(String name, boolean required, TemplateDisplay display) {
        super(name, required, display);
        templateDisplayMap.put("IMAGE", new ImageItem("image", true, new BasicTemplateDisplay("")));
        templateDisplayMap.put("TEXT", new TextTemplateItem("text", true, new BasicTemplateDisplay("")));
    }

    @Override
    public boolean supports(String value) {
        return "emphasisType".equals(value);
    }

    @Override
    public void validItem(JsonNode template) {
        TemplateDisplay templateDisplay = templateDisplayMap.get(template.get("emphasisType").asText());
        templateDisplay.validItem(template);
    }
}
