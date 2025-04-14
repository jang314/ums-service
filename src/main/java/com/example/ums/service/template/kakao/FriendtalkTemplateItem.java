package com.example.ums.service.template.kakao;

import com.example.ums.service.template.TemplateItemComponent;
import com.example.ums.validator.ArrayValidator;
import com.example.ums.validator.ItemValidator;
import com.example.ums.validator.TypeValidator;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

public class FriendtalkTemplateItem extends KakaoTemplateItem {
    @Autowired
    public FriendtalkTemplateItem() {
        items.add(getImage());
        items.add(getButtonValidator());
    }

    @Override
    public boolean supports(String channel, String serviceType) {
        return super.supports(channel, serviceType) && "friendtalk".equals(serviceType);
    }

    @Override
    public TemplateItemComponent validate(JsonNode jsonNode) {
        return super.validate(jsonNode);
    }


    private TypeValidator getButtonValidator() {
        TypeValidator type = new ItemValidator("type", "string", true);
        TypeValidator label = new ItemValidator("label", "string", true);
        TypeValidator url = new ItemValidator("url", "string", true);
        TypeValidator schema = new ItemValidator("schema", "string", false);
        TypeValidator button = new ItemValidator("buttons", "jsonList",
                type, label, url, schema);
        return new ArrayValidator(button, 1, 20);
    }


    private TypeValidator getImage() {
        return new ItemValidator("image", "jsonObject",
                    new ItemValidator("width", "int"),
                    new ItemValidator("height", "int"),
                    new ItemValidator("imageUrl", "string", true)
                );
    }
}
