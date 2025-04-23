package com.example.ums.service.template.kakao;

import com.example.ums.service.ChannelStrategy;
import com.example.ums.service.template.item.BasicTemplateDisplay;
import com.example.ums.service.template.item.TemplateDisplay;
import com.example.ums.service.template.item.TemplateItemDecorator;
import com.example.ums.service.template.item.template_type.TemplateTypeItem;
import com.fasterxml.jackson.databind.JsonNode;

public abstract class KakaoTemplate  implements ChannelStrategy {


    @Override
    public boolean getChannel(String channel) {
        return "kakao".equals(channel);
    }

}
