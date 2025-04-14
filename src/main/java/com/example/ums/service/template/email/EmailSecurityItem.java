package com.example.ums.service.template.email;


import com.example.ums.validator.ArrayValidator;
import com.example.ums.validator.FileValidator;
import com.example.ums.validator.ItemValidator;
import com.example.ums.validator.TypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailSecurityItem extends EmailTemplateItem {

    @Autowired
    public EmailSecurityItem() {
        TypeValidator cover = new ItemValidator("cover", "string", true);
        TypeValidator security = new ItemValidator("files", "array", true);
        TypeValidator array = new ArrayValidator(security, 1, 10);

        items.add(new FileValidator(cover, "html")); // 파일 존재 유무 및 확장자 검증
        items.add(new FileValidator(array, "html"));
    }



    @Override
    protected boolean supports(String serviceType) {
        return "security".equals(serviceType);
    }
}
