package com.example.ums.service.template.item;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

public class ItemListItem extends TemplateItemDecorator {
    private String item;
    private String value;

    public ItemListItem(String name, boolean required, TemplateDisplay templateDisplay) {
        super(name, required, templateDisplay);
    }


    @Override
    public boolean supports(String value) {
        return "itemList".equals(value);
    }

    @Override
    public void validItem(JsonNode template) {
        JsonNode itemList = template.get("itemList");

        for (JsonNode list : itemList) {
            if(list.has("type")) {
                throw new IllegalArgumentException("itemList.type is empty.");
            }

            if(!list.has("name")) {
                throw new IllegalArgumentException("itemList.name is empty.");
            }
        }
    }
}
