package com.example.ums.service.template.item;

public record FieldType(String field, boolean required) {
    public FieldType(String field) {
        this(field, true);
    }
}
