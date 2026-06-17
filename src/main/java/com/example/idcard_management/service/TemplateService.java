package com.example.idcard_management.service;

import com.example.idcard_management.model.Template;

import java.util.List;

public interface TemplateService {

    Template create(Template template);

    Template update(Long id, Template template);

    void delete(Long id);

    Template getById(Long id);

    List<Template> getAll();

    Template findByTemplateName(String templateName);

    boolean existsByTemplateName(String templateName);
}