package com.example.ums.service.template;

import com.example.ums.dto.ITemplate;
import com.example.ums.service.template.email.EmailSecurityItem;
import com.example.ums.service.template.email.EmailTemplateDto;
import com.fasterxml.jackson.databind.JsonNode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TemplateItemRegistryTest {
    @Autowired
    private TemplateItemRegistry registry;

    @Test
    public void make() {
        // Given
        ITemplateDto email = new EmailTemplateDto("subject", "header", "body", "footer", "cover",
                new String[]{"/usr/html/security1.html"});

//        TemplateDto templateDto = new TemplateDto("템플릿 등록 테스트", "E", "security", email);

//        TemplateItemComponent component = registry.make(templateDto);

//        assertThat(component).isInstanceOf(EmailSecurityItem.class);
    }

    @Test
    void validate_test() {
        // Given
        ITemplateDto email = new EmailTemplateDto("subject", "header", "body", "footer", "cover",
                new String[]{"D:/index.html", "D:/me.png"});

//        TemplateDto templateDto = new TemplateDto("템플릿 등록 테스트", "E", "attachment", email);

//        registry.validate(templateDto);
    }

    @Test
    void jsonNode_Test(){
//        JsonNode jsonNode =
    }

}