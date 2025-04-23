package com.example.ums.validator;

public class TypeItem extends TemplateItemDecorator {
    private String[] types;

    public TypeItem(TemplateItem validator, String... types) {
        super(validator);
        this.types = types;
    }

}
