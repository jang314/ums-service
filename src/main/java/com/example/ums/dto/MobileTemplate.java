package com.example.ums.dto;

public record MobileTemplate(Long id, String subject, String content) {
    MobileTemplate(String subject, String content) {
        this(null, subject,content);
    }
    static MobileTemplate createSmsTemplate(Long id, String content) {
        return new MobileTemplate(id, null, content);
    }
    static MobileTemplate createMmsTemplate(Long id, String subject, String content) {
        return new MobileTemplate(id, subject, content);
    }
}

