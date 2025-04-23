package com.example.ums.service.template.kakao;

import com.example.ums.service.template.item.BasicTemplateDisplay;
import com.example.ums.service.template.item.FieldType;
import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.template_type.*;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class FriendtalkTemplate extends KakaoTemplate  {
    private final List<TemplateDisplay> templateDisplayList = new ArrayList<>();

    @Autowired
    public FriendtalkTemplate(@Qualifier("baTemplateType") TemplateDisplay baTemplateType,
                              @Qualifier("imTemplateType") TemplateDisplay imTemplateType
                              ) {
        this.templateDisplayList.add(baTemplateType);
        this.templateDisplayList.add(imTemplateType);
    }

    @Override
    public boolean getServiceType(String serviceType) {
        return "friendtalk".equals(serviceType);
    }

    @Override
    public JsonNode makeTemplate(JsonNode template) {
        String templateType = template.get("templateType").asText();

        TemplateDisplay display = templateDisplayList.stream()
                .filter(dp -> dp.supports("templateType", templateType))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Not valid template type"));

        display.validate(template);
        return template;
    }
}
