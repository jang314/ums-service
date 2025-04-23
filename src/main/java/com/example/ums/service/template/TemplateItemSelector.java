package com.example.ums.service.template;

import com.example.ums.service.ChannelStrategy;
import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.template_type.TemplateTypeItem;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class TemplateItemSelector {
    private final List<ChannelStrategy> channelStrategies;

    public void make(TemplateDto request) {
        String channel = request.channel();
        String serviceType = request.serviceType();
        JsonNode template = request.template();
        channelStrategies.stream()
                .filter(ch -> ch.supports(channel, serviceType))
                .map(ch -> ch.makeTemplate(request.template()))
                .collect(Collectors.toList());

    }
}