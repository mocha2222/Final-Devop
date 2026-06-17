package com.example.idcard_management.controller;

import com.example.idcard_management.model.Template;
import com.example.idcard_management.service.TemplateService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping
    public List<Template> getAllTemplates() {
        return templateService.getAll();
    }

    @GetMapping("/{id}")
    public Template getTemplateById(
            @PathVariable Long id) {

        return templateService.getById(id);
    }

    @GetMapping("/name/{templateName}")
    public Template getByTemplateName(
            @PathVariable String templateName) {

        return templateService.findByTemplateName(templateName);
    }

    @GetMapping("/exists/{templateName}")
    public boolean existsByTemplateName(
            @PathVariable String templateName) {

        return templateService.existsByTemplateName(templateName);
    }

    @PostMapping
    public Template createTemplate(
            @RequestBody Template template) {

        return templateService.create(template);
    }

    @PutMapping("/{id}")
    public Template updateTemplate(
            @PathVariable Long id,
            @RequestBody Template template) {

        return templateService.update(id, template);
    }

    @DeleteMapping("/{id}")
    public String deleteTemplate(
            @PathVariable Long id) {

        templateService.delete(id);

        return "Template deleted successfully";
    }
}