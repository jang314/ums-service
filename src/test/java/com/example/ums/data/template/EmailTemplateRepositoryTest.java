package com.example.ums.data.template;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmailTemplateRepositoryTest {
    @Autowired private TemplateRepository templateRepository;
    @Autowired private TemplateItemRepository templateItemRepository;
    @Autowired private TemplateItemUseRepository useRepository;

    @Test
    @DisplayName("이메일 템플릿 등록 시 값 검증")
    void email_template_save_test() {
        // given
        EmailTemplate template = makeEmailTemplate();

        // when
        EmailTemplate saveTemplate = templateRepository.save(template);

        // then
        assertThat(saveTemplate.getId()).isNotNull();
        assertThat(saveTemplate.getServiceType()).isEqualTo(template.getServiceType());
        assertThat(saveTemplate.getTemplateName()).isEqualTo(template.getTemplateName());
        assertThat(saveTemplate.getSubject()).isEqualTo(template.getSubject());
        assertThat(saveTemplate.getItems().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("이메일 템플릿 조회 시 관련 값 검증")
    void email_template_find_test() {
        // given
        EmailTemplate saveTemplate = templateRepository.save(makeEmailTemplate());

        // when
        EmailTemplate resultTemplate = (EmailTemplate) templateRepository.findById(saveTemplate.getId()).get();
        List<TemplateItem> items = templateItemRepository.findAll();
        List<TemplateItemUse> useItems = useRepository.findAll();

        // then
        assertThat(resultTemplate.getId()).isNotNull();
        assertThat(resultTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
        assertThat(resultTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
        assertThat(resultTemplate.getItems().size()).isEqualTo(3);
        assertThat(resultTemplate.getSubject()).isEqualTo(saveTemplate.getSubject());

        assertThat(items).hasSize(3);
        assertThat(useItems).hasSize(3);
    }

    @Test
    @DisplayName("이메일 템플릿 데이터 값 수정 시 정상 처리되는지 검증")
    void template_update_value_test() {
        // given
        EmailTemplate saveTemplate = templateRepository.save(makeEmailTemplate());
        TemplateItem[] items =  saveTemplate.getItems().stream()
                .map(itemUse -> new TemplateItem(itemUse.getItem().getId(), itemUse.getItem().getItemType(), itemUse.getItem().getItemValue(), itemUse.getItem().getItemExt()))
                .collect(Collectors.toList()).toArray(TemplateItem[]::new);

        // when
        Template updateTemplate = Template.modify(saveTemplate.getId(), new EmailTemplate("이메일 제목-수정본"), items);
        Template resultTemplate = templateRepository.save(updateTemplate);

        // then
        assertThat(resultTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
        assertThat(resultTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
        assertThat(((EmailTemplate)resultTemplate).getSubject()).isEqualTo(saveTemplate.getSubject());
        assertThat(resultTemplate.getItems()).hasSize(3);
    }

    @Test
    @DisplayName("템플릿 아이템 추가 등록 시 템플릿 사용 및 아이템 데이터가 정상 추가 되는지 검증")
    void template_add_item_test() {
        // given
        EmailTemplate saveTemplate = templateRepository.save(makeEmailTemplate());

        List<TemplateItem> items =  saveTemplate.getItems().stream()
                .map(itemUse -> new TemplateItem(itemUse.getItem().getId(), itemUse.getItem().getItemType(), itemUse.getItem().getItemValue(), itemUse.getItem().getItemExt()))
                .collect(Collectors.toList());
        items.add(new TemplateItem(null, "cover", "/uplaod/cover.html", "html"));
        items.add(new TemplateItem(null, "file", "/uplaod/cover.img", "img"));


        // when
        EmailTemplate newTemplate = new EmailTemplate(saveTemplate.getId(), "이메일 템플릿_수정본", "리마인드 이메일입니다. ", "SECURITY", items.toArray(TemplateItem[]::new));
        EmailTemplate updateTemplate = templateRepository.save(newTemplate);

        List<TemplateItem> findItems = templateItemRepository.findAll();
        List<TemplateItemUse> findItemsUse = useRepository.findAll();

        // then
        assertThat(updateTemplate.getItems()).hasSize(5);
        assertThat(findItems).hasSize(5);
        assertThat(findItemsUse).hasSize(5);
    }

    @Test
    @DisplayName("템플릿 아이템 일부 제거 시 템플릿 사용 및 아이템 데이터가 정상 삭제 되는지 검증")
    void template_remove_item_test() {
        // given
        EmailTemplate saveTemplate = templateRepository.save(makeEmailTemplate());
        saveTemplate.getItems().removeIf(itemUse -> itemUse.getItem().getItemType().equals("header"));

        // when
        EmailTemplate emailTemplate = new EmailTemplate("이메일_템플릿_수정본");
        Template newTemplate = Template.modify(saveTemplate.getId(),
                emailTemplate,
                saveTemplate.getItems().stream()
                        .map(useItem ->
                                new TemplateItem(useItem.getItem().getId(), useItem.getItem().getItemType(),
                                        useItem.getItem().getItemValue(),
                                        useItem.getItem().getItemExt())).toArray(TemplateItem[]::new)
        );

        Template updateTemplate = templateRepository.save(newTemplate);

        EmailTemplate resultTemplate = (EmailTemplate) templateRepository.findById(updateTemplate.getId()).get();
        List<TemplateItem> findItems = templateItemRepository.findAll();
        List<TemplateItemUse> findItemsUse = useRepository.findAll();

        // then
        assertThat(resultTemplate.getItems()).hasSize(2);
        assertThat(updateTemplate.getItems()).hasSize(2);
        assertThat(findItems).hasSize(3);
        assertThat(findItemsUse).hasSize(2);
    }

    @Test
    @DisplayName("템플릿 아이템 삭제 시 템플릿과 사용 데이터는 삭제되지 않는지 검증")
    void template_item_delete_test() {
        // given
        EmailTemplate saveTemplate = templateRepository.save(makeEmailTemplate());

        // when
        List<Long> itemIds = templateItemRepository.findAll().stream().map(items -> items.getId()).collect(Collectors.toList());
        templateItemRepository.deleteAll();
        List<Template> templates = templateRepository.findAll();
        List<TemplateItemUse> useItems = useRepository.findAll();

        // then
        assertThat(itemIds).hasSize(3);
        assertThat(templates).hasSize(1);
        assertThat(useItems).hasSize(3);
    }

    private EmailTemplate makeEmailTemplate(){
        List<TemplateItem> items = new ArrayList<>(Arrays.asList(
                new TemplateItem(null, "header", "이메일 헤더", "html"),
                new TemplateItem(null, "body", "이메일 본문", "html"),
                new TemplateItem(null, "footer", "이메일 하단", "html"))
        );
        return new EmailTemplate(null,"이메일 템플릿", "#{name}님의 #{month}월 청구서 입니다. ", "HTML", items.toArray(TemplateItem[]::new));
    }
}