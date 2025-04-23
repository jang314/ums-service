package com.example.ums.service.template;

public interface TemplateService {
    boolean supports(TemplateDto templateDto);
    void validate(TemplateDto templateDto);
}
