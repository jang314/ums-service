package com.example.ums.data.template;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("MOBILE")
public class MobileTemplate extends Template {
    private String subject;
    private String content;

    public MobileTemplate(Long id, String templateName, String serviceType, String subject, String content, TemplateItem... items) {
        super(id, templateName, serviceType, items);
        this.subject = subject;
        this.content = content;
    }

    public MobileTemplate(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    @Override
    protected void applyTo(Template toTemplate) {
        this.subject = ((MobileTemplate) toTemplate).getSubject();
        this.content = ((MobileTemplate) toTemplate).getContent();
    }
}
