package com.example.ums.dto;

public record KakaoTemplate(Long id, String templateCode, String senderKey, String text) {
    KakaoTemplate(String templateCode, String senderKey, String text) {
        this(null, templateCode, senderKey, text);
    }
    KakaoTemplate(String senderKey, String text) {
        this(null, null, senderKey, text);
    }
}
