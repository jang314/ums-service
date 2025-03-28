package com.example.ums.controller;

import com.example.ums.request.template.TemplateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TemplateController implements ITemplateController {


    @PostMapping("/templates")
    public void save(@RequestBody TemplateRequest request) {

    }
}
