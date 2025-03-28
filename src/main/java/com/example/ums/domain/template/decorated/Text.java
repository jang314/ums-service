package com.example.ums.domain.template.decorated;

public class Text implements IParser {


    @Override
    public String parse(String message) {
        return message;
    }
}
