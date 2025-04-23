package com.example.ums.service.template.kakao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AlimtalkTemplateTest {

    @Test
    void 템플릿_만들기_테스트() {
        System.out.println("템플릿 만들기");
    }

    private Map<String, Object> requestMap() {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("templateCd", "TMPL_ALT_202504211120001022");
        requestMap.put("kakaoSenderKey", "");
        requestMap.put("templateType", "BA");


        return requestMap;
    }

}