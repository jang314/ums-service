package com.example.ums.validator;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonValidator extends ItemAdditionalValidator {


    public JsonValidator(TypeValidator validator) {
        super(validator);
    }

    public JsonValidator(TypeValidator validator, Class<? extends JsonRequest> clazz) {
        super(validator);
    }

    @Override
    public JsonNode validate(JsonNode items) {

        return super.validate(items);
    }
}
