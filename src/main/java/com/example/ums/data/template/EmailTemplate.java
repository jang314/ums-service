package com.example.ums.data.template;

import com.example.ums.service.template.email.EmailTemplateDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@DiscriminatorValue("EMAIL")
public class EmailTemplate extends Template {
    private String subject;
    private String header;
    private String body;
    private String footer;

    public EmailTemplate(Long id, String templateName, String subject, String serviceType, TemplateItem... items) {
        super(id, templateName, serviceType, items);
        this.subject = subject;
    }

    public static EmailTemplate defaultTemplate(EmailTemplateDto templateDto) {
        EmailTemplate emailTemplate = new EmailTemplate(
                templateDto.subject(),
                templateDto.header(),
                templateDto.body(),
                templateDto.footer()
                );

        return emailTemplate;
    }
    private EmailTemplate(String subject, String header, String body, String footer) {
        this.subject = subject;
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    public EmailTemplate(String subject) {
        this.subject = subject;
    }

    @Override
    protected void applyTo(Template toTemplate) {
        this.subject = ((EmailTemplate) toTemplate).getSubject();
    }
}
