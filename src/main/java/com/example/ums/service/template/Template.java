package com.example.ums.service.template;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class Template implements TemplateItemComponent {
    private String type;
    private boolean required;

    private List<TemplateItemComponent> components;

    public Template(String type, boolean required) {
        this.type = type;
        this.required = required;
    }

    public Template(String type) {
        this.type = type;
        this.required = false;
    }

    public void add(TemplateItemComponent component) {
        this.add(component);
    }
}
