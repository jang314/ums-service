package com.example.ums.data.template;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MobilelTemplateRepositoryTest {
    @Autowired private TemplateRepository templateRepository;
    @Autowired private TemplateItemRepository templateItemRepository;
    @Autowired private TemplateItemUseRepository useRepository;

    @Test
    @DisplayName("모바일 템플릿 등록 시 값 검증")
    void mobile_template_save_test() {
        // given
        MobileTemplate template = makeMobileTemplate();

        // when
        MobileTemplate saveTemplate = templateRepository.save(template);

        // then
        assertThat(saveTemplate.getId()).isNotNull();
        assertThat(saveTemplate.getServiceType()).isEqualTo(template.getServiceType());
        assertThat(saveTemplate.getTemplateName()).isEqualTo(template.getTemplateName());
        assertThat(saveTemplate.getSubject()).isEqualTo(template.getSubject());
        assertThat(saveTemplate.getContent()).isEqualTo(template.getContent());
        assertThat(saveTemplate.getItems().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("모바일 템플릿 조회 시 관련 값 검증")
    void email_template_find_test() {
        // given
        MobileTemplate saveTemplate = templateRepository.save(makeMobileTemplate());

        // when
        MobileTemplate resultTemplate = (MobileTemplate) templateRepository.findById(saveTemplate.getId()).get();
        List<TemplateItem> items = templateItemRepository.findAll();
        List<TemplateItemUse> useItems = useRepository.findAll();

        // then
        assertThat(resultTemplate.getId()).isNotNull();
        assertThat(resultTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
        assertThat(resultTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
        assertThat(resultTemplate.getItems().size()).isEqualTo(2);
        assertThat(resultTemplate.getSubject()).isEqualTo(saveTemplate.getSubject());

        assertThat(items).hasSize(2);
        assertThat(useItems).hasSize(2);
    }

    @Test
    @DisplayName("모바일 템플릿 데이터 값 수정 시 정상 처리되는지 검증")
    void template_update_value_test() {
        // given
        MobileTemplate saveTemplate = templateRepository.save(makeMobileTemplate());
        TemplateItem[] items =  saveTemplate.getItems().stream()
                .map(itemUse -> new TemplateItem(itemUse.getItem().getId(), itemUse.getItem().getItemType(), itemUse.getItem().getItemValue(), itemUse.getItem().getItemExt()))
                .collect(Collectors.toList()).toArray(TemplateItem[]::new);

        // when
        Template updateTemplate = Template.modify(saveTemplate.getId(), new MobileTemplate("MMS 제목", "MMS 내용"), items);
        MobileTemplate resultTemplate = (MobileTemplate) templateRepository.save(updateTemplate);

        // then
        assertThat(resultTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
        assertThat(resultTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
        assertThat(resultTemplate.getSubject()).isEqualTo(saveTemplate.getSubject());
        assertThat(resultTemplate.getContent()).isEqualTo(saveTemplate.getContent());
        assertThat(resultTemplate.getItems()).hasSize(2);
    }

    @Test
    @DisplayName("모바일 아이템 추가 등록 시 템플릿 사용 및 아이템 데이터가 정상 추가 되는지 검증")
    void template_add_item_test() {
        // given
        MobileTemplate saveTemplate = templateRepository.save(makeMobileTemplate());

        List<TemplateItem> items =  saveTemplate.getItems().stream()
                .map(itemUse -> new TemplateItem(itemUse.getItem().getId(), itemUse.getItem().getItemType(), itemUse.getItem().getItemValue(), itemUse.getItem().getItemExt()))
                .collect(Collectors.toList());

        items.add(new TemplateItem(null, "img", "/uplaod/cover.jpg", "jpg"));
        items.add(new TemplateItem(null, "img", "/uplaod/cover.png", "png"));


        // when
        MobileTemplate newTemplate = new MobileTemplate(saveTemplate.getId(), "이메일 템플릿_수정본", "MMS", "제목","문자 내용", items.toArray(TemplateItem[]::new));
        MobileTemplate updateTemplate = templateRepository.save(newTemplate);

        List<TemplateItem> findItems = templateItemRepository.findAll();
        List<TemplateItemUse> findItemsUse = useRepository.findAll();

        // then
        assertThat(updateTemplate.getItems()).hasSize(4);
        assertThat(findItems).hasSize(4);
        assertThat(findItemsUse).hasSize(4);
    }

    @Test
    @DisplayName("모바일 아이템 일부 제거 시 템플릿 사용 및 아이템 데이터가 정상 삭제 되는지 검증")
    void template_remove_item_test() {
        // given
        MobileTemplate saveTemplate = templateRepository.save(makeMobileTemplate());
        saveTemplate.getItems().removeIf(itemUse -> itemUse.getItem().getItemExt().equals("jpg"));

        // when
        MobileTemplate mobileTemplate = new MobileTemplate("MMS_템플릿_수정본","템플릿수정본");
        Template newTemplate = Template.modify(saveTemplate.getId(),
                mobileTemplate,
                saveTemplate.getItems().stream()
                        .map(useItem ->
                                new TemplateItem(useItem.getItem().getId(), useItem.getItem().getItemType(),
                                        useItem.getItem().getItemValue(),
                                        useItem.getItem().getItemExt())).toArray(TemplateItem[]::new)
        );

        Template updateTemplate = templateRepository.save(newTemplate);

        MobileTemplate resultTemplate = (MobileTemplate) templateRepository.findById(updateTemplate.getId()).get();
        List<TemplateItem> findItems = templateItemRepository.findAll();
        List<TemplateItemUse> findItemsUse = useRepository.findAll();

        // then
        assertThat(resultTemplate.getItems()).hasSize(1);
        assertThat(updateTemplate.getItems()).hasSize(1);
        assertThat(findItems).hasSize(2);
        assertThat(findItemsUse).hasSize(1);
    }

    @Test
    @DisplayName("모바일 아이템 삭제 시 템플릿과 사용 데이터는 삭제되지 않는지 검증")
    void template_item_delete_test() {
        // given
         MobileTemplate saveTemplate = templateRepository.save(makeMobileTemplate());

        // when
        List<Long> itemIds = templateItemRepository.findAll().stream().map(items -> items.getId()).collect(Collectors.toList());
        templateItemRepository.deleteAll();
        List<TemplateItem> items = templateItemRepository.findAll();
        List<Template> templates = templateRepository.findAll();
        List<TemplateItemUse> useItems = useRepository.findAll();

        // then
        assertThat(items).hasSize(0);
        assertThat(itemIds).hasSize(2);
        assertThat(templates).hasSize(1);
        assertThat(useItems).hasSize(3);
    }


    private MobileTemplate makeMobileTemplate(){
        List<TemplateItem> items = new ArrayList<>(Arrays.asList(
                new TemplateItem(null, "img", "/upload/img/1.jpg", "jpg"),
                new TemplateItem(null, "img", "/upload/img/2.png", "png")
        ));

        return new MobileTemplate(null, "MMS 템플릿", "MMS","MMS 제목", "MMS본문", items.toArray(TemplateItem[]::new));
    }
}