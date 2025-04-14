package com.example.ums.data.template;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TemplateItemRepositoryTest {
    @Autowired private TemplateItemRepository itemRepository;
    @Autowired private TemplateRepository templateRepository;
    @Autowired private TemplateItemUseRepository useRepository;

    @Test
    @DisplayName("템플릿 아이템 등록 테스트")
    void template_item_save_test() {
        // given
        TemplateItem header = new TemplateItem(null, "header", "D:/workspace/header.html", "html");
        TemplateItem body = new TemplateItem(null, "body", "D:/workspace/body.html", "html");
        TemplateItem footer = new TemplateItem(null, "footer", "D:/workspace/footer.html", "html");

        // when
        List<TemplateItem> saveItems = itemRepository.saveAll(Arrays.asList(header, body, footer));

        // then
        assertThat(saveItems.size()).isEqualTo(3);
        assertThat(saveItems)
                .usingRecursiveComparison()
                .isEqualTo(Arrays.asList(header, body, footer));
    }

    @Test
    @DisplayName("템플릿 아이템 등록 테스트")
    void template_item_find_test() {
        // given
        TemplateItem highlight = new TemplateItem(null, "highlight", "{\"title\":\"강조표기문구\", \"subTitle\": \"강조표기문구2\"}", "jsonObject");
        TemplateItem additional = new TemplateItem(null, "addtional", "{\"extra_info\": {\"order_date\":\"2025-03-28\", \"delivery_date\":\"2025-03-29\"}", "jsonObject");
        TemplateItem button = new TemplateItem(null, "buttons", "[{\"button_type\" : \"상세보기\", \"button_url\":\"https://test.com/order/123\"}]", "jsonList");
        TemplateItem itemList = new TemplateItem(null, "itemList", "[{\"item_name\" : \"주문번호\", \"item_value\":\"1234567890\"}]", "jsonList");
        List<TemplateItem> saveItems = itemRepository.saveAll(Arrays.asList(highlight, additional, button, itemList));

        // when
        List<TemplateItem> findItems = itemRepository.findAll();

        // then
        assertThat(findItems).hasSize(4);
        assertThat(findItems)
                .usingRecursiveComparison()
                .isEqualTo(saveItems);
    }

//    @Test
//    @DisplayName("템플릿 아이템 수정 테스트")
//    void template_item_update_test() {
//        // given
//        TemplateItem jpg = new TemplateItem("img", "D:/workspace/upload/123.jpg", "jpg");
//        TemplateItem saveItem = itemRepository.save(jpg);
//
//        // when
//        TemplateItem png = new TemplateItem(saveItem.getId(), "img", "D:/workspace/upload/456/png", "png");
//        TemplateItem updateItem = itemRepository.save(png);
//
//        // then
//        assertThat(updateItem.getId()).isEqualTo(saveItem.getId());
//        assertThat(updateItem.getItemType()).isEqualTo(saveItem.getItemType());
//        assertThat(updateItem.getItemValue()).isEqualTo(saveItem.getItemValue());
//        assertThat(updateItem.getItemExt()).isEqualTo(saveItem.getItemExt());
//    }

    @Test
    @DisplayName("템플릿 아이템 삭제 테스트")
    void template_item_delete_test() {
        // given

        // when

        // then
    }
}