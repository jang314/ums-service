package com.example.ums.service;

import com.example.ums.service.template.ServiceTypeStrategy;
import com.example.ums.service.template.TemplateDto;
import com.fasterxml.jackson.databind.JsonNode;

public interface ChannelStrategy {
    default boolean supports(String channel, String serviceType) {
        return getChannel(channel) && getServiceType(serviceType);
    }

    boolean getChannel(String channel);
    boolean getServiceType(String serviceType);

    JsonNode makeTemplate(JsonNode template);
}
