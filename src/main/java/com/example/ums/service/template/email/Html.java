package com.example.ums.service.template.email;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class Html extends EmailTemplateItem {
    private String header;
    private String body;
    private String footer;
}
