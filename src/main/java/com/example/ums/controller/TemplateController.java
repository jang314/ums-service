package com.example.ums.controller;

import com.example.ums.service.template.TemplateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor
public class TemplateController {

    @PostMapping
    @ResponseBody
    public ResponseEntity save(@RequestBody TemplateDto template) {
      log.info("templateDTO = {}", template);
      return ResponseEntity.ok(template);
    }
}
