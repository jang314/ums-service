package com.example.ums.service.template.item;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public class FileItem extends TemplateItemDecorator {
    private String filePath;
    private String fileName;

    public FileItem(String name, boolean required, TemplateDisplay templateDisplay) {
        super(name, required, templateDisplay);
    }


    @Override
    public boolean supports(String value) {
        return "file".equals(value);
    }


    @Override
    public JsonNode validate(JsonNode template) {
        return null;
    }

    @Override
    public void validItem(JsonNode template) {

    }
}
