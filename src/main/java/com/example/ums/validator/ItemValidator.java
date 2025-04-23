package com.example.ums.validator;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public record ItemValidator (String name, String type, boolean required, List<TemplateItem> typeValidator) implements TemplateItem {

    public ItemValidator(String name, String type) {
        this(name, type, false, null);
    }

    public ItemValidator(String name, String type, boolean required) {
        this(name, type, required, null);
    }

    public ItemValidator(String name, String type, TemplateItem... typeValidators) {
        this(name, type, false, Arrays.asList(typeValidators));
    }

    private boolean supports(String name, String value) {
        return name.equals(this.name) && (value != null && value.trim().length() > 0);
    }

    @Override
    public JsonNode validate(JsonNode itemList) {
//        List<ItemDto> list = itemList.stream()
//                .filter(item -> supports(item.type(), item.value()))
//                .collect(Collectors.toList());

//        if(this.required && list.size() == 0) {
//            throw new IllegalArgumentException(name+" must be not empty.");
//        }
//        return list;
        return null;
    }

}
