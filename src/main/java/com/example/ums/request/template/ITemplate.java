package com.example.ums.request.template;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "channel")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailTemplateRequest.class, name = "E"),
        @JsonSubTypes.Type(value = SmsTemplateRequest.class, name = "S"),
        @JsonSubTypes.Type(value = LmsTemplateRequest.class, name = "L"),
        @JsonSubTypes.Type(value = AlimtalkTemplateRequest.class, name = "A"),
        @JsonSubTypes.Type(value = FriendtalkTemplateRequest.class, name = "F")
})
public sealed interface ITemplate permits EmailTemplateRequest, SmsTemplateRequest, MmsTemplateRequest, LmsTemplateRequest, AlimtalkTemplateRequest, FriendtalkTemplateRequest {
}
