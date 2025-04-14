package com.example.ums.data.template;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class KakaoTemplateRepositoryTest {
    @Autowired private TemplateRepository templateRepository;
    @Autowired private TemplateItemRepository templateItemRepository;
    @Autowired private TemplateItemUseRepository useRepository;

    @Test
    @DisplayName("이메일 템플릿 등록 시 값 검증")
    void email_template_save_test() {
        // given
        KakaoTemplate template = makeAlimtalkTemplate();

        // when
        KakaoTemplate saveTemplate = templateRepository.save(template);

        // then
        assertThat(saveTemplate.getId()).isNotNull();
        assertThat(saveTemplate.getServiceType()).isEqualTo(template.getServiceType());
        assertThat(saveTemplate.getTemplateName()).isEqualTo(template.getTemplateName());
        assertThat(saveTemplate.getText()).isEqualTo(template.getText());
        assertThat(saveTemplate.getTemplateCode()).isEqualTo(template.getTemplateCode());
        assertThat(saveTemplate.getSenderKey()).isEqualTo(template.getSenderKey());
        assertThat(saveTemplate.getItems().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("이메일 템플릿 조회 시 관련 값 검증")
    void email_template_find_test() {
        // given
        KakaoTemplate saveTemplate = templateRepository.save(makeAlimtalkTemplate());

        // when
        KakaoTemplate resultTemplate = (KakaoTemplate) templateRepository.findById(saveTemplate.getId()).get();
        List<TemplateItem> items = templateItemRepository.findAll();
        List<TemplateItemUse> useItems = useRepository.findAll();

        // then
        assertThat(resultTemplate.getId()).isNotNull();
        assertThat(resultTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
        assertThat(resultTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
        assertThat(resultTemplate.getItems().size()).isEqualTo(4);
        assertThat(resultTemplate.getText()).isEqualTo(saveTemplate.getText());
        assertThat(resultTemplate.getTemplateCode()).isEqualTo(saveTemplate.getTemplateCode());
        assertThat(resultTemplate.getSenderKey()).isEqualTo(saveTemplate.getSenderKey());

        assertThat(items).hasSize(4);
        assertThat(useItems).hasSize(4);
    }

    @Test
    @DisplayName("이메일 템플릿 데이터 값 수정 시 정상 처리되는지 검증")
    void template_update_value_test() {
        // given
        KakaoTemplate saveTemplate = templateRepository.save(makeAlimtalkTemplate());
        TemplateItem[] items =  saveTemplate.getItems().stream()
                .map(itemUse -> new TemplateItem(itemUse.getItem().getId(), itemUse.getItem().getItemType(), itemUse.getItem().getItemValue(), itemUse.getItem().getItemExt()))
                .collect(Collectors.toList()).toArray(TemplateItem[]::new);

        // when
        Template updateTemplate = Template.modify(saveTemplate.getId(), new KakaoTemplate("본문", "발신프로필키", "템플릿 코드"), items);
        Template resultTemplate = templateRepository.save(updateTemplate);

        // then
        assertThat(resultTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
        assertThat(resultTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
        assertThat(((KakaoTemplate) resultTemplate).getText()).isEqualTo(saveTemplate.getText());
        assertThat(((KakaoTemplate) resultTemplate).getTemplateCode()).isEqualTo(saveTemplate.getTemplateCode());
        assertThat(((KakaoTemplate) resultTemplate).getSenderKey()).isEqualTo(saveTemplate.getSenderKey());
        assertThat(resultTemplate.getItems()).hasSize(4);
    }

    @Test
    @DisplayName("템플릿 아이템 추가 등록 시 템플릿 사용 및 아이템 데이터가 정상 추가 되는지 검증")
    void template_add_item_test() {
        // given
        KakaoTemplate saveTemplate = templateRepository.save(makeAlimtalkTemplate());

        List<TemplateItem> items =  saveTemplate.getItems().stream()
                .map(itemUse -> new TemplateItem(itemUse.getItem().getId(), itemUse.getItem().getItemType(), itemUse.getItem().getItemValue(), itemUse.getItem().getItemExt()))
                .collect(Collectors.toList());

        items.add(new TemplateItem(null, "cover", "/uplaod/cover.html", "html"));
        items.add(new TemplateItem(null, "file", "/uplaod/cover.img", "img"));

        // when
        KakaoTemplate newTemplate = new KakaoTemplate(saveTemplate.getId(), "템플릿", "이메일 템플릿_수정본", "리마인드 이메일입니다. ","", "", items.toArray(TemplateItem[]::new));
        KakaoTemplate updateTemplate = templateRepository.save(newTemplate);

        List<TemplateItem> findItems = templateItemRepository.findAll();
        List<TemplateItemUse> findItemsUse = useRepository.findAll();

        // then
        assertThat(updateTemplate.getItems()).hasSize(6);
        assertThat(findItems).hasSize(6);
        assertThat(findItemsUse).hasSize(6);
    }

    @Test
    @DisplayName("템플릿 아이템 일부 제거 시 템플릿 사용 및 아이템 데이터가 정상 삭제 되는지 검증")
    void template_remove_item_test() {
        // given
        KakaoTemplate saveTemplate = templateRepository.save(makeAlimtalkTemplate());
        saveTemplate.getItems().removeIf(itemUse -> itemUse.getItem().getItemType().equals("header"));

        // when
        KakaoTemplate emailTemplate = new KakaoTemplate("이메일_템플릿_수정본","","");
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
        KakaoTemplate saveTemplate = templateRepository.save(makeAlimtalkTemplate());

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


    private KakaoTemplate makeAlimtalkTemplate() {
        TemplateItem highlight = new TemplateItem(null, "highlight", "{\"title\":\"강조표기문구\", \"subTitle\": \"강조표기문구2\"}", "jsonObject");
        TemplateItem additional = new TemplateItem(null, "addtional", "{\"extra_info\": {\"order_date\":\"2025-03-28\", \"delivery_date\":\"2025-03-29\"}", "jsonObject");
        TemplateItem button = new TemplateItem(null, "buttons", "[{\"button_type\" : \"상세보기\", \"button_url\":\"https://test.com/order/123\"}]", "jsonList");
        TemplateItem itemList = new TemplateItem(null, "itemList", "[{\"item_name\" : \"주문번호\", \"item_value\":\"1234567890\"}]", "jsonList");
        List<TemplateItem> items =  new ArrayList<>(Arrays.asList(highlight, additional, button, itemList));

        return new KakaoTemplate(null, "알림톡 템플릿", "ALIMTALK","기본본문메시지", "알림톡 템플릿", "tmplCode00001");
    }
}