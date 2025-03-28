package com.example.ums.request.template;

public record MmsTemplateRequest(String subject, String content, String files) implements ITemplate {
}
