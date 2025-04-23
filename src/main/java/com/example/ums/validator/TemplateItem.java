package com.example.ums.validator;

import com.fasterxml.jackson.databind.JsonNode;

public interface TemplateItem {
    JsonNode validate(JsonNode items);
}
