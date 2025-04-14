package com.example.ums.service.template.email;


import com.example.ums.validator.ArrayValidator;
import com.example.ums.validator.FileValidator;
import com.example.ums.validator.ItemValidator;
import com.example.ums.validator.TypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailAttachmentItem extends EmailTemplateItem {

    @Autowired
    public EmailAttachmentItem() {
        TypeValidator file = new ItemValidator("files", "array", true);
        TypeValidator array = new ArrayValidator(file, 1, 10);
        items.add(new FileValidator(array, "html", "jpg", "png", "pdf", "xlsx"));
    }

    @Override
    protected boolean supports(String serviceType) {
        return "attachment".equals(serviceType);
    }

}
