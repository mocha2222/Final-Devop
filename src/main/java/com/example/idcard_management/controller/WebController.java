package com.example.idcard_management.controller;

import com.example.idcard_management.model.Profile;
import com.example.idcard_management.service.ProfileService;
import com.example.idcard_management.util.FileStorageService;
import com.example.idcard_management.util.PdfGenerationService;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final ProfileService profileService;
    private final FileStorageService fileStorageService;
    private final PdfGenerationService pdfGenerationService;
    private final SpringTemplateEngine templateEngine;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("profiles", profileService.getAll());
        return "index";
    }

    @GetMapping("/create")
    public String createCardForm(Model model) {
        model.addAttribute("profile", new Profile());
        return "create_card";
    }

    @PostMapping("/create")
    public String saveCard(
            @ModelAttribute Profile profile,
            @RequestParam("photo") MultipartFile photo) {
        
        if (!photo.isEmpty()) {
            String path = fileStorageService.storeFile(photo);
            profile.setPhotoPath(path);
        }
        
        profileService.create(profile);
        return "redirect:/";
    }

    @GetMapping("/preview/{id}")
    public String previewCard(@PathVariable Long id, Model model) {
        model.addAttribute("profile", profileService.getById(id));
        return "id_card_template";
    }

    @GetMapping("/export/pdf/{id}")
    public ResponseEntity<ByteArrayResource> exportPdf(@PathVariable Long id) {
        Profile profile = profileService.getById(id);
        
        Context context = new Context();
        context.setVariable("profile", profile);
        String html = templateEngine.process("id_card_template", context);
        
        byte[] pdfBytes = pdfGenerationService.generatePdfFromHtml(html);
        
        if (pdfBytes == null) {
            return ResponseEntity.internalServerError().build();
        }

        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=id_card_" + profile.getRegistrationNumber() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(resource);
    }
}
