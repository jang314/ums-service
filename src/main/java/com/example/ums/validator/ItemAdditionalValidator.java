package com.example.ums.validator;

import com.example.ums.service.template.ItemDto;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class ItemAdditionalValidator implements TypeValidator {
    private TypeValidator validator;

    public ItemAdditionalValidator(TypeValidator validator) {
        this.validator = validator;
    }

    @Override
    public JsonNode validate(JsonNode items) {
        log.info("itemAdditionalValidator : " + validator.getClass().getSimpleName());
        return validator.validate(items);
    }
}
