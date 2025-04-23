package com.example.ums.service.template.email;

import com.example.ums.service.ChannelStrategy;
import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.validator.ItemValidator;
import com.example.ums.service.template.TemplateItemComponent;
import com.example.ums.validator.TemplateItem;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class EmailTemplateItem implements ChannelStrategy {

    public EmailTemplateItem() {
    }
    @Override
    public boolean getChannel(String channel) {
        return "email".equals(channel);
    }
}
