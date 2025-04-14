package com.example.ums.service.template.kakao;

import com.example.ums.service.template.TemplateItemComponent;
import com.example.ums.validator.ArrayValidator;
import com.example.ums.validator.ItemValidator;
import com.example.ums.validator.JsonValidator;
import com.example.ums.validator.TypeValidator;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlimtalkTemplateItem extends KakaoTemplateItem {

    @Autowired
    public AlimtalkTemplateItem() {
        items.add(getButtonValidator());
        items.add(getHighlight());
        items.add(getItemList());
    }

    private TypeValidator getButtonValidator() {
        TypeValidator validator = new ItemValidator("buttons", "jsonList");


        return validator;
    }

    private TypeValidator getHighlight() {
        TypeValidator highlight = new ItemValidator("highlight", "jsonObject"
        ,new ItemValidator("title", "string")
        ,new ItemValidator("subTitle", "string"));
        return highlight;
    }

    private TypeValidator getItemList() {
        TypeValidator itemList = new ItemValidator("itemList", "array",
                new ItemValidator("title", "string", true),
                new ItemValidator("description", "string"),
                new ItemValidator("link", "string")
                );
        return new ArrayValidator(itemList, 1, 10);
    }

    @Override
    public boolean supports(String channel, String serviceType) {
        return super.supports(channel, serviceType) && "alimtalk".equals(serviceType);
    }

    @Override
    public TemplateItemComponent validate(JsonNode jsonNode) {
        return super.validate(jsonNode);
    }
}
