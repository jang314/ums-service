package com.example.ums.service.template.item;

import com.example.ums.service.template.item.button.AppLinkButton;
import com.example.ums.service.template.item.button.WeblinkButton;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
// 최대 5개
public class QuickReply extends TemplateItemDecorator{
    private final Map<String, TemplateDisplay> quickReply = new HashMap<>();
    public QuickReply(String name, boolean required, TemplateDisplay templateDisplay) {
        super(name, required, templateDisplay);
        quickReply.put("WL", new WeblinkButton("quickReplies", false, new BasicTemplateDisplay("")));
        quickReply.put("AL", new AppLinkButton("quickReplies", false, new BasicTemplateDisplay("")));
    }

    @Override
    public boolean supports(String value) {
        return "quickReplies".equals(value);
    }


    @Override
    public void validItem(JsonNode template) {
        JsonNode quickReplies = template.get("quickReplies");

        for (JsonNode quickReply : quickReplies) {
            if(!quickReply.has("name")) {
                throw new IllegalArgumentException("name is empty.");
            }

            if (!quickReply.has("type")) {
                throw new IllegalArgumentException("type is empty");
            }

            TemplateDisplay templateDisplay = this.quickReply.get(quickReply.get("type").asText());
            templateDisplay.validItem(quickReply);
        }
    }
}
