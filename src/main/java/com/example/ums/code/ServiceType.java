package com.example.ums.code;

import java.util.ArrayList;
import java.util.List;

public enum ServiceType {
    MOBILE(List.of("SMS", "MMS")),
    EMAIL(List.of("HTML", "PDF", "FILE", "SECURITY")),
    KAKAO(List.of("ALIMTALK", "FRIENDTALK"));

    private final List<String> serviceType;

    ServiceType(List<String> serviceType) {
        this.serviceType = serviceType;
    }
}
