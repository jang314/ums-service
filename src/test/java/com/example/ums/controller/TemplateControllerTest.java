package com.example.ums.controller;

import com.example.ums.service.template.TemplateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TemplateControllerTest {

    @InjectMocks
    private TemplateController templateController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(templateController).build();
    }

    @Test
    void 템플릿_등록_요청_테스트() throws Exception {
        // given
        final String url = "/api/v1/templates";

        // when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(requestJson())
                        .contentType(MediaType.APPLICATION_JSON)

        );

        System.out.println("resultActions : " + resultActions);
    }
    private String requestJson() {
        return " { \"templateName : \"이메일 템플릿 등록 테스트\", "
                + "\"channel\" : \"email\", "
                + "\"serviceType\" : \"security\", "
                + "\"template\" : { "
                + "    \"subject\" : \"10월 청구서 입니다.\" "
                + "    \"cover\" : \"/usr/security/test.html\" "
                + "     \"file\" : [\"/usr/security/test1.html\",\"/usr/security/test2.html\", \"/usr/security/test3/html\"] "
                + "     } "
                + "  "
                ;
    }

}