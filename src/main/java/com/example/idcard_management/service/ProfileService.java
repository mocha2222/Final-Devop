package com.example.idcard_management.service;

import com.example.idcard_management.model.Profile;

import java.util.List;

public interface ProfileService {

    Profile create(Profile profile);

    Profile update(Long id, Profile profile);

    void delete(Long id);

    Profile getById(Long id);

    List<Profile> getAll();

    Profile findByRegistrationNumber(String registrationNumber);

    boolean existsByRegistrationNumber(String registrationNumber);
}