package com.example.ums.domain.template.decorated;

import com.example.ums.domain.template.strategy.TemplateStrategy;
import com.example.ums.domain.template.strategy.TemplateType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

public class TemplateMessage implements ITemplate {
    private String message;

    public TemplateMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        System.out.println(this.message);
        return this.message;
    }
}
