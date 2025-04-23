package com.example.ums.service.template.mobile;

import com.fasterxml.jackson.databind.JsonNode;

public class LmsTemplateItem extends MobileTemplateItem {
    @Override
    public boolean getServiceType(String serviceType) {
        return "lms".equals(serviceType);
    }

    @Override
    public JsonNode makeTemplate(JsonNode template) {
        return null;
    }
}
