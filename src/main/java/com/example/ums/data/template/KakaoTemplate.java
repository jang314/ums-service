package com.example.ums.data.template;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("KAKAO")
@AllArgsConstructor
@NoArgsConstructor
public class KakaoTemplate extends Template {
    private String text;
    private String senderKey;
    private String templateCode;

    public KakaoTemplate(Long id, String templateName, String serviceType, String text, String senderKey, String templateCode, TemplateItem... items) {
        super(id, templateName, serviceType, items);
        this.text = text;
        this.senderKey = senderKey;
        this.templateCode = templateCode;
    }

    @Override
    protected void applyTo(Template toTemplate) {
        this.text = ((KakaoTemplate) toTemplate).getText();
        this.senderKey = ((KakaoTemplate) toTemplate).getSenderKey();
        this.templateCode = ((KakaoTemplate) toTemplate).getTemplateCode();
    }
}
