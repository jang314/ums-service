package com.example.ums.service.template.email;

import com.example.ums.service.template.ItemDto;
import com.example.ums.validator.ItemValidator;
import com.example.ums.service.template.TemplateItemComponent;
import com.example.ums.validator.TypeValidator;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class EmailTemplateItem implements TemplateItemComponent {
    protected final List<TypeValidator> items = new ArrayList<>();

    public EmailTemplateItem() {
        items.add(new ItemValidator("subject", "string"));
        items.add(new ItemValidator("header", "string"));
        items.add(new ItemValidator("body", "string", true));
        items.add(new ItemValidator("footer", "string"));
    }

    @Override
    public boolean supports(String channel, String serviceType) {
        boolean isServiceType = supports(serviceType);
        return "email".equals(channel) && isServiceType;
    }

    @Override
    public TemplateItemComponent validate(JsonNode jsonNode) {
        items.stream()
                .forEach(item -> {
                    log.info(item.getClass().getSimpleName());
                    item.validate(jsonNode);
                });

        return null;
    }

    protected abstract boolean supports(String serviceType);

}
