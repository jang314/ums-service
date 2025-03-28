package com.example.ums.domain.template;

import com.example.ums.domain.template.strategy.TemplateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateFactory {
    private final Map<String, TemplateStrategy> templateStrategyMap;

    public TemplateStrategy getTemplateType(String type) {
        TemplateStrategy templateStrategy = templateStrategyMap.keySet().stream()
                .filter(map -> map.equals(type))
                .map(map -> templateStrategyMap.get(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바른 형식의 템플릿 타입이 아닙니다."));

        return templateStrategy;
    }


}
