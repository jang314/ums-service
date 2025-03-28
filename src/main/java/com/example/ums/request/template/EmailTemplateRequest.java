package com.example.ums.request.template;

public record EmailTemplateRequest(String subject, String content, String files) implements ITemplate {
}
