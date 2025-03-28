package com.example.ums.data.template;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TemplateRepositoryTest {
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired TemplateItemRepository templateItemRepository;
    @Autowired private TestEntityManager testEntityManager;
    @Autowired private TemplateItemUseRepository useRepository;

    @Test
    @DisplayName("템플릿 등록 테스트")
    void template_save() {
        // given
        List<TemplateItem> items = new ArrayList<>();
        items.add(templateItemRepository.save(new TemplateItem("header", "이메일 헤더")));
        items.add(templateItemRepository.save(new TemplateItem("body", "이메일 본문")));
        items.add(templateItemRepository.save(new TemplateItem("footer", "이메일 하단")));

        testEntityManager.flush();
        testEntityManager.clear();

        Template template = new EmailTemplate("이메일 템플릿", "#{name}님의 #{month}월 청구서 입니다. ","HTML", items.toArray(TemplateItem[]::new));

        // when
        Template saveTemplate = templateRepository.save(template);
        testEntityManager.flush();
        testEntityManager.clear();

        // then
        assertThat(saveTemplate.getItems().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("템플릿 조회 테스트")
    void template_select() {
        // given
        List<TemplateItem> items = new ArrayList<>();
        items.add(templateItemRepository.save(new TemplateItem("header", "이메일 헤더")));
        items.add(templateItemRepository.save(new TemplateItem("body", "이메일 본문")));
        items.add(templateItemRepository.save(new TemplateItem("footer", "이메일 하단")));
        items.add(templateItemRepository.save(new TemplateItem("pdf", "htmlToPdf")));

        testEntityManager.flush();
        testEntityManager.clear();

        EmailTemplate saveTemplate = templateRepository.save(new EmailTemplate("이메일 템플릿", "#{name}님의 #{month}월 청구서 입니다. ","HTML", items.toArray(TemplateItem[]::new)));
        testEntityManager.flush();
        testEntityManager.clear();

        // when
        EmailTemplate findTemplate = (EmailTemplate)  templateRepository.findById(saveTemplate.getId()).get();

        // then
        assertThat(findTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
        assertThat(findTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
        assertThat(findTemplate.getSubject()).isEqualTo(saveTemplate.getSubject());
        assertThat(findTemplate.getItems().size()).isEqualTo(4);
    }
}