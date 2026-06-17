package com.example.idcard_management.controller;

import com.example.idcard_management.model.Profile;
import com.example.idcard_management.service.ProfileService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAll();
    }

    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable Long id) {
        return profileService.getById(id);
    }

    @GetMap("/registration/{registrationNumber}")
    public Profile getByRegistrationNumber(
            @PathVariable String registrationNumber) {

        return profileService.findByRegistrationNumber(registrationNumber);
    }

    @PostMapping
    public Profile createProfile(
            @RequestBody Profile profile) {

        return profileService.create(profile);
    }

    @PutMapping("/{id}")
    public Profile updateProfile(
            @PathVariable Long id,
            @RequestBody Profile profile) {

        return profileService.update(id, profile);
    }

    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable Long id) {

        profileService.delete(id);

        return "Profile deleted successfully";
    }
}
