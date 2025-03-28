package com.example.ums.domain.template.decorated;

public class JonmunParser extends ParserDecorator {
    private final String json ;
    public JonmunParser(IParser parser, String json) {
        super(parser);
        this.json = json;
    }

    @Override
    public String parse(String message) {
        super.parse(message);
        return json + " ---> " + message;
    }
}
