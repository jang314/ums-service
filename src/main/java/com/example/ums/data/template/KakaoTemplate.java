package com.example.ums.data.template;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("KAKAO")
public class KakaoTemplate extends Template {
    private String text;
    private String senderKey;
    private String templateCode;
}
