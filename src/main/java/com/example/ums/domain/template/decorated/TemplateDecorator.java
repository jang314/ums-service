package com.example.ums.domain.template.decorated;

import com.example.ums.domain.template.strategy.TemplateStrategy;
import com.example.ums.domain.template.strategy.TemplateType;

public class TemplateDecorator implements ITemplate {
    private ITemplate template;

    public TemplateDecorator(ITemplate template) {
        this.template = template;
    }

    @Override
    public String getMessage() {
        return template.getMessage();
    }
}
