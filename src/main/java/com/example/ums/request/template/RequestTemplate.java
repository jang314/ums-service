package com.example.ums.request.template;

import java.util.List;

public record RequestTemplate(Long id,
                              String channel,
                              String serviceType,
                              String title,
                              String template,
                              List<RequestItem> itemList) {
}
