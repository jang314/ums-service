package com.example.ums.service.template.item.template_type;

import com.example.ums.config.TemplateItemConfig;
import com.example.ums.service.template.item.TemplateDisplay;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BATemplateTypeItemTest {
    @Autowired
    private TemplateItemConfig config;

    @Test
    void 빈_등록_테스트(){
        TemplateDisplay templateDisplay = config.baTemplateType();
        assertThat(templateDisplay).isInstanceOf(BATemplateTypeItem.class);
    }

}