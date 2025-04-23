package com.example.ums.service.template.item;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageItem extends TemplateItemDecorator {
    private String url;
    private String link;
    private int width;
    private int height;

    public ImageItem(String name, boolean required, TemplateDisplay display) {
        super(name, required, display);
    }

    @Override
    public boolean supports(String value) {
        log.info("image.field={}",getField());
        return "IMAGE".equals(value);
    }


    @Override
    public void validItem(JsonNode template) {
        log.info("image valid ");
        //20x660, 500kb
    }
}
