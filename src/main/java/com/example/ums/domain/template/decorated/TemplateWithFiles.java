package com.example.ums.domain.template.decorated;

import com.example.ums.domain.template.strategy.TemplateStrategy;

public class TemplateWithFiles extends TemplateDecorator {
    private String fileName;
    private String filePath;

    public TemplateWithFiles(ITemplate template, String... files) {
        super(template);
        this.fileName = files[0];
        this.filePath = files[1];
    }

    @Override
    public String getMessage() {
        super.getMessage();
        return this.fileName + "\\/" + this.filePath;
    }
}
