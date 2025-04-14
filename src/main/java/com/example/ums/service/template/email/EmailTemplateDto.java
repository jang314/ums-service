package com.example.ums.service.template.email;

import com.example.ums.data.template.EmailTemplate;
import com.example.ums.data.template.Template;
import com.example.ums.service.template.ITemplateDto;
import com.example.ums.service.template.ItemDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record EmailTemplateDto(
                                String subject,
                                String header,
                                String body,
                                String footer,
                                ItemDto... items) implements ITemplateDto {

    // attachment, htmlToPdf
    public EmailTemplateDto(String subject, String header, String body, String footer, String... files) {
        this(subject, header, body,footer,
                Arrays.stream(files)
                        .map(file ->  new ItemDto("files", file))
                        .toArray(ItemDto[]::new));
    }

    // security, htmlToPdf
    public EmailTemplateDto(String subject, String header, String body, String footer, String cover, String... files) {
        this(subject, header, body,footer,
                Stream.concat(Arrays.stream(files)
                                .map(file -> new ItemDto("files", file)),
                                Stream.of(new ItemDto("cover",cover)))
                        .toArray(ItemDto[]::new)) ;
    }

    public EmailTemplateDto(String subject, String header, String body, String footer) {
        this(subject, header, body, footer, new ItemDto[0]);
    }

    @Override
    public List<ItemDto> getItems() {
        List<ItemDto> result = Arrays.stream(items).collect(Collectors.toList());
        result.add(new ItemDto("subject", subject));
        result.add(new ItemDto("header", header));
        result.add(new ItemDto("body", body));
        result.add(new ItemDto("footer", footer));
        return result;
    }

}
