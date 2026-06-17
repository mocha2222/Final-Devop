package com.example.idcard_management.service.impl;

import com.example.idcard_management.model.Profile;
import com.example.idcard_management.repository.ProfileRepository;
import com.example.idcard_management.service.ProfileService;
import com.example.idcard_management.util.BarcodeService;
import com.example.idcard_management.util.RegistrationNumberGenerator;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final BarcodeService barcodeService;

    @Override
    public Profile create(Profile profile) {
        if (profile.getRegistrationNumber() == null || profile.getRegistrationNumber().isEmpty()) {
            profile.setRegistrationNumber(RegistrationNumberGenerator.generateId(profile.getProfileType(), profile.getDepartment()));
        }

        // Generate base64 barcodes
        profile.setBarcodePath(barcodeService.generateBarcodeBase64(profile.getRegistrationNumber(), 200, 50));
        profile.setQrCodePath(barcodeService.generateQRCodeBase64(profile.getRegistrationNumber(), 150, 150));

        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Long id, Profile profile) {

        Profile existing = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existing.setFirstName(profile.getFirstName());
        existing.setLastName(profile.getLastName());
        existing.setEmail(profile.getEmail());
        existing.setPhone(profile.getPhone());
        existing.setDepartment(profile.getDepartment());
        existing.setDateOfBirth(profile.getDateOfBirth());
        existing.setProfileType(profile.getProfileType());
        
        if (profile.getPhotoPath() != null) {
            existing.setPhotoPath(profile.getPhotoPath());
        }

        return profileRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile getById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile findByRegistrationNumber(String registrationNumber) {
        return profileRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @Override
    public boolean existsByRegistrationNumber(String registrationNumber) {
        return profileRepository.existsByRegistrationNumber(registrationNumber);
    }
}