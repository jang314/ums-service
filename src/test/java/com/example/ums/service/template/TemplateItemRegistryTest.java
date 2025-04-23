package com.example.ums.service.template;

import com.example.ums.service.template.email.EmailTemplateDto;
import com.example.ums.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TemplateItemRegistryTest {
    @Autowired
    private TemplateItemSelector registry;

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
    void decorated_template_test() throws JsonProcessingException {
        List<Map<String, Object>> buttons = new ArrayList<>();
        Map<String, Object> button = new HashMap<>();
        button.put("type", "WL");
        button.put("name", "웹링크");
        buttons.add(button);

        button = new HashMap<>();
        button.put("type", "AL");
        button.put("name", "앱링크");
        buttons.add(button);

        Map<String, Object> requestMap = new HashMap<>();
        Map<String, Object> image = new HashMap<>();
        image.put("url","test");
        image.put("link","sddd");
        requestMap.put("kakaoSenderKey", "tmpl-abcdefghijk1234567890");
        requestMap.put("templateType", "BA");
        requestMap.put("templateCd", "BA");
        requestMap.put("body", "test");
        requestMap.put("title", "test");
        requestMap.put("emphasisType", "TEXT");
        requestMap.put("image", image);
        requestMap.put("buttons", buttons);

        String template = JsonUtil.toJson(requestMap);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(template);
        TemplateDto templateDto = new TemplateDto("템플릿 등록 테스트", "kakao","alimtalk", jsonNode , null);

        registry.make(templateDto);
    }

}