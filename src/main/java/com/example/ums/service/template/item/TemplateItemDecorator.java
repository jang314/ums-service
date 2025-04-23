package com.example.ums.service.template.item;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

@Slf4j
@Getter
public abstract class TemplateItemDecorator extends TemplateDisplay {
    private final TemplateDisplay templateDisplay;

    public TemplateItemDecorator(String field, boolean required, TemplateDisplay templateDisplay) {
        super(field, required);
        this.templateDisplay = templateDisplay;
    }

//    public boolean supports(String field, String value){
//        log.info("deco");
//        boolean result = templateDisplay.supports(field, value);
//        return result && supports(value);
//    }
    @Override
    public boolean supports(String field, String value) {
        return templateDisplay.getField().equals(field)
                || templateDisplay.supports(value);
    }

    @Override
    public JsonNode validate(JsonNode template){
        JsonNode validate = templateDisplay.validate(template);
        String field = templateDisplay.getField();
        boolean required = templateDisplay.isRequired();
        Iterator<String> stringIterator = template.fieldNames();

        while(stringIterator.hasNext()) {
            String k = stringIterator.next();
            String value = template.has(field) ? template.get(field).asText() : "";
//            if(templateDisplay.supports(k, value)) {
//                templateDisplay.validItem(template);
//            }
            if(field.equals(k)) {
                templateDisplay.validItem(template);
            }
        }

        return template;
    }
}
