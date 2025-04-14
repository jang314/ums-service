package com.example.ums.validator;

import com.example.ums.service.template.ItemDto;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface TypeValidator {
    JsonNode validate(JsonNode items);
}
