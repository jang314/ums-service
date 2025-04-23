package com.example.ums.config;

import com.example.ums.service.template.item.*;
import com.example.ums.service.template.item.template_type.BATemplateTypeItem;
import com.example.ums.service.template.item.template_type.BITemplateTypeItem;
import com.example.ums.service.template.item.template_type.IMTemplateTypeItem;
import com.example.ums.service.template.item.template_type.LITemplateTypeItem;
import com.example.ums.service.template.kakao.AlimtalkTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemplateItemConfig {
    @Bean
    public TemplateDisplay baTemplateType() {
        TemplateDisplay display = new BasicTemplateDisplay("serviceType");
        display  = new HighlightItem("emphasisType", false, display);
        display = new ButtonItem("buttons", false, display);
        display = new QuickReply("quickReplies", false, display);
        return new BATemplateTypeItem(display);
    }


//    @Bean
//    public TemplateDisplay alimtalkTemplate(){
//        TemplateDisplay template = new AlimtalkTemplate();
//        baTemplateType(template);
//        biTemplateType(template);
//        liTemplateType(template);
//        return template;
//    }

    @Bean
    public TemplateDisplay biTemplateType() {
        TemplateDisplay display = new BasicTemplateDisplay("serviceType");
        display  = new ItemListItem("itemList", true, display);
        display = new ImageItem("image", true, display);
        display = new QuickReply("quickReplies", false, display);
        display = new ButtonItem("buttons", false, display);
        return new BITemplateTypeItem(display);
    }

    @Bean
    public TemplateDisplay imTemplateType() {
        TemplateDisplay display = new BasicTemplateDisplay("serviceType");
        display = new ImageItem("image", true, display);
        display = new QuickReply("quickReplies", false, display);
        display = new ButtonItem("buttons", false, display);
        return new IMTemplateTypeItem(display);
    }

    @Bean
    public TemplateDisplay liTemplateType() {
        TemplateDisplay display = new BasicTemplateDisplay("serviceType");
        display  = new ItemListItem("itemList", true, display);
        display = new QuickReply("quickReplies", false, display);
        display = new ButtonItem("buttons", false, display);
        return new LITemplateTypeItem(display);
    }
}
