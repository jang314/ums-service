package com.example.ums.service.template.kakao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class Alimtalk extends KakaoTemplateItem {
    private String hightlight;
    private String additional;
    private String button;
    private String itemList;
}
