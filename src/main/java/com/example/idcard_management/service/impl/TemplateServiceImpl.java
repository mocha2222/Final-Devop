package com.example.idcard_management.service.impl;

import com.example.idcard_management.model.Template;
import com.example.idcard_management.repository.TemplateRepository;
import com.example.idcard_management.service.TemplateService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    @Override
    public Template create(Template template) {
        return templateRepository.save(template);
    }

    @Override
    public Template update(Long id, Template template) {

        Template existing = templateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        existing.setTemplateName(template.getTemplateName());
        existing.setTemplateFile(template.getTemplateFile());
        existing.setActive(template.isActive());

        return templateRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        templateRepository.deleteById(id);
    }

    @Override
    public Template getById(Long id) {
        return templateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }

    @Override
    public List<Template> getAll() {
        return templateRepository.findAll();
    }

    @Override
    public Template findByTemplateName(String templateName) {
        return templateRepository.findByTemplateName(templateName)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }

    @Override
    public boolean existsByTemplateName(String templateName) {
        return templateRepository.existsByTemplateName(templateName);
    }
}