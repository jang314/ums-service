package com.example.ums.domain.template.decorated;

import com.example.ums.domain.template.strategy.TemplateStrategy;

public class TemplateWithButtons extends TemplateDecorator{
    private String name;
    private String type;

    public TemplateWithButtons(ITemplate template, String json) {
        super(template);
        this.type = json.split(",")[0];
        this.name = json.split(",")[1];
    }

    @Override
    public String getMessage() {
        super.getMessage();
        return this.type + ":" +this.name;
    }
}
