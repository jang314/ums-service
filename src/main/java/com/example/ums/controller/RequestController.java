package com.example.ums.controller;


import com.example.ums.request.RequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RequestController {

    @PostMapping("/message")
    public void requestMessage(@RequestBody RequestMessage command) {

    }
}
