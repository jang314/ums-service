package com.example.ums.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestMessage {
    private final String templateId; // CHANNEL-UUID
    private final String requestId;
    private final String targetId;
    private final String requestDtm;
}
