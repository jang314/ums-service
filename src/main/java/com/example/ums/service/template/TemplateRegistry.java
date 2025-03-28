package com.example.ums.service.template;

import com.example.ums.service.template.email.Attachment;
import com.example.ums.service.template.email.EmailTemplateItem;
import com.example.ums.service.template.email.Html;
import com.example.ums.service.template.email.HtmlToPdf;
import com.example.ums.service.template.kakao.Alimtalk;
import com.example.ums.service.template.kakao.Friendtalk;
import com.example.ums.service.template.kakao.KakaoTemplateItem;
import com.example.ums.service.template.mobile.Lms;
import com.example.ums.service.template.mobile.Mms;
import com.example.ums.service.template.mobile.MobileTemplateItem;
import com.example.ums.service.template.mobile.Sms;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemplateRegistry {
    private final Template template;


//    private interface ItemComponent {
//
//    }
//
//    private static class TemplateItem implements ItemComponent {
//        String type;
//
//        private TemplateItem(String type) {
//            this.type = type;
//        }
//    }
//
//    private static class Template implements ItemComponent {
//        String type;
//        List<ItemComponent> components = new ArrayList<>();
//
//        private Template(String type) {
//            this.type = type;
//        }
//
//
//        public void add(ItemComponent component) {
//            this.components.add(component);
//        }
//    }
}
