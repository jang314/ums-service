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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TemplateRepositoryTest {
    @Autowired private TemplateRepository templateRepository;
    @Autowired private TemplateItemRepository templateItemRepository;
    @Autowired private TemplateItemUseRepository useRepository;

//    @Test
//    @DisplayName("이메일 템플릿 등록 시 값 검증.")
//    void email_template_save_test() {
//        // given
//        EmailTemplate template = makeEmailTemplate();
//
//        // when
//        EmailTemplate saveTemplate = templateRepository.save(template);
//
//        // then
//        assertThat(saveTemplate.getId()).isNotNull();
//        assertThat(saveTemplate.getServiceType()).isEqualTo(template.getServiceType());
//        assertThat(saveTemplate.getTemplateName()).isEqualTo(template.getTemplateName());
//        assertThat(saveTemplate.getSubject()).isEqualTo(template.getSubject());
//        assertThat(saveTemplate.getItems().size()).isEqualTo(3);
//    }
//
//    @Test
//    @DisplayName("모바일 템플릿 등록 시 값 검증.")
//    void mobile_template_save_test() {
//        // given
//        MobileTemplate template = makeMobileTemplate();
//
//        // when
//        MobileTemplate saveTemplate = templateRepository.save(template);
//
//        // then
//        assertThat(saveTemplate.getId()).isNotNull();
//        assertThat(saveTemplate.getServiceType()).isEqualTo(template.getServiceType());
//        assertThat(saveTemplate.getTemplateName()).isEqualTo(template.getTemplateName());
//        assertThat(saveTemplate.getItems().size()).isEqualTo(2);
//        assertThat(saveTemplate.getSubject()).isEqualTo(template.getSubject());
//        assertThat(saveTemplate.getContent()).isEqualTo(template.getContent());
//    }
//
//
//    @Test
//    @DisplayName("카카오 템플릿 등록 시 값 검증.")
//    void kakao_template_save_test() {
//        // given
//        KakaoTemplate template = makeAlimtalkTemplate();
//
//        // when
//        KakaoTemplate saveTemplate = templateRepository.save(template);
//
//        // then
//        assertThat(saveTemplate.getId()).isNotNull();
//        assertThat(saveTemplate.getServiceType()).isEqualTo(template.getServiceType());
//        assertThat(saveTemplate.getTemplateName()).isEqualTo(template.getTemplateName());
//        assertThat(saveTemplate.getItems().size()).isEqualTo(4);
//        assertThat(saveTemplate.getText()).isEqualTo(template.getText());
//        assertThat(saveTemplate.getSenderKey()).isEqualTo(template.getSenderKey());
//        assertThat(saveTemplate.getTemplateCode()).isEqualTo(template.getTemplateCode());
//    }
//
//    @Test
//    @DisplayName("이메일 템플릿 조회 시 관련 값 검증")
//    void email_template_find_test() {
//        // given
//        EmailTemplate saveTemplate = templateRepository.save(makeEmailTemplate());
//
//        // when
//        EmailTemplate resultTemplate = (EmailTemplate) templateRepository.findById(saveTemplate.getId()).get();
//        List<TemplateItem> items = templateItemRepository.findAll();
//        List<TemplateItemUse> useItems = useRepository.findAll();
//
//        // then
//        assertThat(resultTemplate.getId()).isNotNull();
//        assertThat(resultTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
//        assertThat(resultTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
//        assertThat(resultTemplate.getItems().size()).isEqualTo(3);
//        assertThat(resultTemplate.getSubject()).isEqualTo(saveTemplate.getSubject());
//
//        assertThat(items).hasSize(3);
//        assertThat(useItems).hasSize(3);
//    }
//
//    @Test
//    @DisplayName("모바일 템플릿 조회 시 관련 값 검증")
//    void mobile_template_find_test() {
//        // given
//        MobileTemplate saveTemplate = templateRepository.save(makeMobileTemplate());
//
//        // when
//        MobileTemplate resultTemplate = (MobileTemplate) templateRepository.findById(saveTemplate.getId()).get();
//        List<TemplateItem> items = templateItemRepository.findAll();
//        List<TemplateItemUse> useItems = useRepository.findAll();
//
//        // then
//        assertThat(resultTemplate.getId()).isNotNull();
//        assertThat(resultTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
//        assertThat(resultTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
//        assertThat(resultTemplate.getItems().size()).isEqualTo(2);
//        assertThat(resultTemplate.getSubject()).isEqualTo(saveTemplate.getSubject());
//
//        assertThat(items).hasSize(2);
//        assertThat(useItems).hasSize(2);
//    }
//
//    @Test
//    @DisplayName("카카오 템플릿 조회 시 관련 값 검증")
//    void kakao_template_find_test() {
//        // given
//        KakaoTemplate saveTemplate = templateRepository.save(makeAlimtalkTemplate());
//
//        // when
//        KakaoTemplate resultTemplate = (KakaoTemplate) templateRepository.findById(saveTemplate.getId()).get();
//        List<TemplateItem> items = templateItemRepository.findAll();
//        List<TemplateItemUse> useItems = useRepository.findAll();
//
//        // then
//        assertThat(resultTemplate.getId()).isNotNull();
//        assertThat(resultTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
//        assertThat(resultTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
//        assertThat(resultTemplate.getItems().size()).isEqualTo(4);
//        assertThat(resultTemplate.getText()).isEqualTo(saveTemplate.getText());
//        assertThat(resultTemplate.getTemplateCode()).isEqualTo(saveTemplate.getTemplateCode());
//        assertThat(resultTemplate.getSenderKey()).isEqualTo(saveTemplate.getSenderKey());
//        assertThat(items).hasSize(4);
//        assertThat(useItems).hasSize(4);
//    }
//
//    @Test
//    @DisplayName("템플릿 데이터 값 수정 시 정상 처리되는지 검증")
//    void template_update_value_test() {
//        // given
//        EmailTemplate saveTemplate = templateRepository.save(makeEmailTemplate());
//
//        // when
//        EmailTemplate updateTemplate = new EmailTemplate(saveTemplate.getId(),"이메일템플릿1", "제목-수정","TEXT", saveTemplate.getItems().toArray(TemplateItem[]::new));
//        EmailTemplate resultTemplate = templateRepository.save(updateTemplate);
//
//        // then
//        assertThat(resultTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
//        assertThat(resultTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
//        assertThat(resultTemplate.getSubject()).isEqualTo(saveTemplate.getSubject());
//        assertThat(resultTemplate.getItems()).hasSize(3);
//    }
//
//    @Test
//    @DisplayName("템플릿 아이템 추가 등록 시 템플릿 사용 및 아이템 데이터가 정상 추가 되는지 검증")
//    void template_update_test() {
//        // given
//        List<TemplateItem> items = makeItems();
//        EmailTemplate saveTemplate = templateRepository.save(new EmailTemplate("이메일 템플릿", "#{name}님의 #{month}월 청구서 입니다. ", "HTML", items.toArray(TemplateItem[]::new)));
//
//        items.add(new TemplateItem("PDF", "D:/upload/pdf/1.html","html"));
//        items.add(new TemplateItem("PDF", "D:/upload/pdf/2.html","html"));
//
//        // when
//        EmailTemplate updateTemplate = templateRepository.save(new EmailTemplate(saveTemplate.getId(),"이메일템플릿1", "제목-수정","TEXT", items.toArray(TemplateItem[]::new)));
//
//        // then
//        List<TemplateItem> findItems = templateItemRepository.findAll();
//        List<TemplateItemUse> findItemsUse = useRepository.findAll();
//        assertThat(updateTemplate.getItems()).hasSize(5);
//        assertThat(findItems).hasSize(5);
//        assertThat(findItemsUse).hasSize(5);
//    }
//
//    @Test
//    @DisplayName("템플릿에 등록된 아이템 삭제 시 템플릿 아이템은 삭제되지 않고 사용 테이블만 함께 사용되는지 확인")
//    void template_update_item_remove_test() {
//        // given
//        List<TemplateItem> items = makeAlimtalkItems();
//
//
//        List<TemplateItem> updateItems = makeAlimtalkItems();
//        updateItems.remove(templateItemRepository.findByItemType("buttons"));
//        updateItems.remove(templateItemRepository.findByItemType("itemList"));
//
//        EmailTemplate updateTemplate = templateRepository.save(new EmailTemplate(saveTemplate.getId(),"이메일템플릿1", "제목-수정","TEXT", items.toArray(TemplateItem[]::new)));
//        List<TemplateItem> findItems = templateItemRepository.findAll();
//        List<TemplateItemUse> findItemsUse = useRepository.findAll();
//
//        assertThat(updateTemplate.getServiceType()).isEqualTo(saveTemplate.getServiceType());
//        assertThat(updateTemplate.getTemplateName()).isEqualTo(saveTemplate.getTemplateName());
//        assertThat(updateTemplate.getSubject()).isEqualTo(saveTemplate.getSubject());
//        assertThat(updateTemplate.getItems()).hasSize(5);
//        assertThat(findItems).hasSize(5);
//        assertThat(findItemsUse).hasSize(5);
//    }
//
//    @Test
//    @DisplayName("템플릿 삭제 시 템플릿 아이템은 삭제되지 않는지 검증한다.")
//    void template_remove_test() {
//        // given
//        List<TemplateItem> items = makeItems();
//        EmailTemplate saveTemplate = templateRepository.save(new EmailTemplate("이메일 템플릿", "#{name}님의 #{month}월 청구서 입니다. ", "HTML", items.toArray(TemplateItem[]::new)));
//        Long id = saveTemplate.getId();
//
//        // when
//        templateRepository.delete(saveTemplate);
//
//        // then
//        Optional<Template> findTemplate = templateRepository.findById(id);
//        List<TemplateItem> findItem = templateItemRepository.findAll();
//        List<TemplateItemUse> useItems = useRepository.findAll();
//
//        assertThat(findTemplate).isEmpty();
//        assertThat(useItems).hasSize(0);
//        assertThat(findItem).hasSize(3);
//    }
//
////    @Test
////    @DisplayName("템플릿 아이템 삭제 시 템플릿은 삭제되지 않는다.")
////    void
//
//    private EmailTemplate makeEmailTemplate(){
//        List<TemplateItem> items = new ArrayList<>(Arrays.asList(
//                new TemplateItem("header", "이메일 헤더", "html"),
//                new TemplateItem("body", "이메일 본문", "html"),
//                new TemplateItem("footer", "이메일 하단", "html"))
//        );
//
//        return new EmailTemplate(null,"이메일 템플릿", "#{name}님의 #{month}월 청구서 입니다. ", "HTML", items.toArray(TemplateItem[]::new));
//    }
//
//    private KakaoTemplate makeAlimtalkTemplate() {
//        TemplateItem highlight = new TemplateItem("highlight", "{\"title\":\"강조표기문구\", \"subTitle\": \"강조표기문구2\"}", "jsonObject");
//        TemplateItem additional = new TemplateItem("addtional", "{\"extra_info\": {\"order_date\":\"2025-03-28\", \"delivery_date\":\"2025-03-29\"}", "jsonObject");
//        TemplateItem button = new TemplateItem("buttons", "[{\"button_type\" : \"상세보기\", \"button_url\":\"https://test.com/order/123\"}]", "jsonList");
//        TemplateItem itemList = new TemplateItem("itemList", "[{\"item_name\" : \"주문번호\", \"item_value\":\"1234567890\"}]", "jsonList");
//        List<TemplateItem> items =  new ArrayList<>(Arrays.asList(highlight, additional, button, itemList));
//
//        return new KakaoTemplate(null, "알림톡 템플릿", "ALIMTALK","기본본문메시지", "알림톡 템플릿", "tmplCode00001");
//    }
//
//    private MobileTemplate makeMobileTemplate(){
//        List<TemplateItem> items = new ArrayList<>(Arrays.asList(
//                new TemplateItem("img", "/upload/img/1.jpg", "jpg"),
//                new TemplateItem("img", "/upload/img/2.png", "png")
//        ));
//
//        return new MobileTemplate(null, "MMS 템플릿", "MMS","MMS 제목", "MMS본문", items.toArray(TemplateItem[]::new));
//    }


}