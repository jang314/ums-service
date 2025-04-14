package com.example.ums.service.template;

import com.fasterxml.jackson.databind.JsonNode;

public record TemplateDto  (
        String templateName,
        String channel,
        String serviceType,
        JsonNode template,
        ITemplateDto template1) {
}
