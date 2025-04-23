package com.example.ums.service.template;


import com.example.ums.service.template.item.TemplateDisplay;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public abstract class TemplateItemComponent {
    private final TemplateDisplay templateDisplay;

    public boolean supports(TemplateDto templateDto) {
//        return templateDisplay.supports(templateDto);
        return true;
    }

    protected abstract boolean supports(String type);
}
