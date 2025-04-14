package com.example.ums.service.template.email;

import com.example.ums.validator.ArrayValidator;
import com.example.ums.validator.FileValidator;
import com.example.ums.validator.ItemValidator;
import com.example.ums.validator.TypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailPdfItem extends EmailTemplateItem {

    @Autowired
    public EmailPdfItem() {
        TypeValidator pdf = new ItemValidator("pdf", "array", true);
        TypeValidator array = new ArrayValidator(new FileValidator(pdf, "html"), 1, 10);
        TypeValidator cover = new FileValidator(new ItemValidator("cover", "string"), "html");
        items.add(array);
        items.add(cover);
    }

    @Override
    protected boolean supports(String serviceType) {
        return "pdf".equals(serviceType);
    }
}
