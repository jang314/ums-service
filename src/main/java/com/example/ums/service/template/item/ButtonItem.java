package com.example.ums.service.template.item;

import com.example.ums.service.template.item.button.AppLinkButton;
import com.example.ums.service.template.item.button.BotKeywordButton;
import com.example.ums.service.template.item.button.MessageDeliveryButton;
import com.example.ums.service.template.item.button.WeblinkButton;
import com.example.ums.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
// 알림톡은 5개, 친구톡은3개
public class ButtonItem extends TemplateItemDecorator {
    private final Map<String,TemplateDisplay> button = new HashMap<>();

    public ButtonItem(String field, boolean required, TemplateDisplay templateDisplay) {
        super(field, required, templateDisplay);
        button.put("WL", new WeblinkButton("buttons", false, new BasicTemplateDisplay("")));
        button.put("AL", new AppLinkButton("buttons", false, new BasicTemplateDisplay("")));
        button.put("BK", new BotKeywordButton("buttons", false, new BasicTemplateDisplay("")));
        button.put("MD", new MessageDeliveryButton("buttons", false, new BasicTemplateDisplay("")));
        button.put("AC", new BasicTemplateDisplay(""));
        button.put("DS", new BasicTemplateDisplay(""));
    }

    @Override
    public boolean supports(String value) {
        return "buttons".equals(value);
    }

    @Override
    public void validItem(JsonNode template) {
        JsonNode buttons = template.get("buttons");

        for (JsonNode jsonNode : buttons) {
            if(!jsonNode.has("type")) {
                throw new IllegalArgumentException("type is required");
            }

            if(!button.containsKey(jsonNode.get("type").asText())) {
               throw new IllegalArgumentException("Not Support button type");
            }
            this.button.get(jsonNode.get("type").asText()).validItem(jsonNode);
        }

    }
}
