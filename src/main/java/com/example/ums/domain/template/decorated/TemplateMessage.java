package com.example.ums.domain.template.decorated;

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
