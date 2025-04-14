package com.example.ums.service.template;


import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface TemplateItemComponent  {
    boolean supports(String channel, String serviceType);
    TemplateItemComponent validate(JsonNode jsonNode);
}
