package com.example.ums.service.template.kakao;

import com.example.ums.code.ChannelType;
import com.example.ums.service.template.TemplateItemComponent;
import com.example.ums.validator.ItemValidator;
import com.example.ums.validator.TypeValidator;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class KakaoTemplateItem implements TemplateItemComponent {
    protected final List<TypeValidator> items = new ArrayList<>();

    @Autowired
    public KakaoTemplateItem() {
        items.add(new ItemValidator("kakaoSenderKey", "string"));
        items.add(new ItemValidator("templateType", "string"));
        items.add(new ItemValidator("name", "string"));
    }

    @Override
    public boolean supports(String channel, String serviceType) {
        return "kakao".equals(channel);
    }

    @Override
    public TemplateItemComponent validate(JsonNode jsonNode) {
        return null;
    }

    private void templateType() {
        TypeValidator validator = new ItemValidator("templateType", "string", true);

    }
}