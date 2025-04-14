package com.example.ums.dto;

import com.example.ums.service.template.TemplateItemComponent;

public record TemplateDto(Long id,
                           String templateName,
                           String channel,
                           String serviceType,
                           TemplateItemComponent template) {

    TemplateDto(String templateName, String channel, String serviceType, TemplateItemComponent template){
        this(null, templateName, channel, serviceType, template);
    }
}
