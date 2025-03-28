package com.example.ums.domain.template.decorated;

import com.example.ums.domain.template.strategy.TemplateStrategy;

public class TemplateWithSubject extends TemplateDecorator {
    private final String subject;

    public TemplateWithSubject(ITemplate template, String subject) {
        super(template);
        this.subject = subject;
    }

    @Override
    public String getMessage() {
        super.getMessage();
        System.out.println(this.subject);
        return this.subject;
    }
}
