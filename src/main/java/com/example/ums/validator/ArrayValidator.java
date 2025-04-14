package com.example.ums.validator;

import com.example.ums.service.template.ItemDto;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.util.List;

@Getter
public class ArrayValidator extends ItemAdditionalValidator  {
    private int minSize = 0;
    private int maxSize = 0;

    public ArrayValidator(TypeValidator validator) {
        super(validator);
    }

    public ArrayValidator(TypeValidator validator, int minSize) {
        super(validator);
        this.minSize = minSize;
    }

    public ArrayValidator(TypeValidator validator, int minSize, int maxSize) {
        super(validator);
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public JsonNode validate(JsonNode items) {
        JsonNode list = super.validate(items);
        isLessThan(list.size());
        isRatherThan(list.size());
        return list;
    }

    private void isLessThan(int size) {
        if(size < minSize) {
            throw new IllegalArgumentException("this size too small");
        }
    }

    private void isRatherThan(int size) {
        if(maxSize > 0 && size > maxSize) {
            throw new IllegalArgumentException("this size too many");
        }
    }
}
