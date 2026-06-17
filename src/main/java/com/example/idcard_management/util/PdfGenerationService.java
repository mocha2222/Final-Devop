package com.example.idcard_management.util;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGenerationService {

    public byte[] generatePdfFromHtml(String htmlContent) {
        try {
            ByteArrayOutputStream target = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(htmlContent, target);
            return target.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
