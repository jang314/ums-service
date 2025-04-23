package com.example.ums.service.template.mobile;

import com.example.ums.dto.TemplateDto;
import com.example.ums.service.ChannelStrategy;
import com.example.ums.service.template.TemplateItemComponent;

public abstract class MobileTemplateItem implements ChannelStrategy {
    @Override
    public boolean supports(String channel, String serviceType) {
        return getChannel(channel) && getServiceType(serviceType);
    }

    @Override
    public boolean getChannel(String channel) {
        return "mobile".equals(channel);
    }
}
