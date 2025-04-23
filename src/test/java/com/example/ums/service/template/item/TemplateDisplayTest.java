package com.example.ums.service.template.item;

import com.example.ums.service.template.TemplateDto;
import com.example.ums.service.template.item.template_type.BATemplateTypeItem;
import com.example.ums.service.template.item.template_type.TemplateTypeItem;
import com.example.ums.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class TemplateDisplayTest {
    @Test
    void validate_test() throws JsonProcessingException {

//        TemplateTypeItem result = new BATemplateTypeItem(display);
//        result.validates(makeTemplate());
    }

    private JsonNode makeTemplate() throws JsonProcessingException {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("kakaoSenderKey", "tmpl-abcdefghijk1234567890");
        requestMap.put("templateType", "BA");
        requestMap.put("templateCd", "BA");
        requestMap.put("body", "test");
        requestMap.put("title", "test");
        requestMap.put("emphasisType", "IMAGE");
        requestMap.put("url", "IMAGE");

        String template = JsonUtil.toJson(requestMap);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(template);
//        TemplateDto templateDto = new TemplateDto("템플릿 등록 테스트", "kakao","alimtalk", jsonNode , null);
        return jsonNode;
    }
}