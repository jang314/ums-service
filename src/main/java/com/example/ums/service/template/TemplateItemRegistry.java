package com.example.ums.service.template;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class TemplateItemRegistry {
    private final List<TemplateItemComponent> component;

    public void printItems() {
        component.stream().forEach(component -> {
            log.info("TemplateItemRegistry = {}",  component.getClass().getSimpleName());
            component.validate(null);
        });
    }

    public TemplateItemComponent make(TemplateDto template) {
        return component.stream()
                .filter(comp -> comp.supports(template.channel(), template.serviceType()))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException("Not Supports Channel And Service"));
    }

    public void validate(TemplateDto templateDto) {
        TemplateItemComponent component = make(templateDto);
        component.validate(templateDto.template());
    }
}
