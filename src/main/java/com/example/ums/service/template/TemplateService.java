package com.example.ums.service.template;

import com.example.ums.data.template.Template;
import com.example.ums.data.template.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
** @return EntityToDto
*/
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;

    public void save() {

    }
}
