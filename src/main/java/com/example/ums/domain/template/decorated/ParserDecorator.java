package com.example.ums.domain.template.decorated;

public abstract class ParserDecorator implements IParser {
    private IParser parser;

    public ParserDecorator(IParser parser) {
        this.parser = parser;
    }

    @Override
    public String parse(String message) {
        return parser.parse(message);
    }
}
