package com.example.ums.data.template;

import com.example.ums.code.ServiceType;
import com.example.ums.request.template.EmailTemplateRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@DiscriminatorValue("EMAIL")
public class EmailTemplate extends Template {
    private String subject;

    public EmailTemplate(String templateName, String subject, String serviceType, TemplateItem... items) {
        this.subject = subject;

    }
}
