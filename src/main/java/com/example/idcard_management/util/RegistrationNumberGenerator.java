package com.example.idcard_management.util;

import com.example.idcard_management.model.ProfileType;

import java.time.Year;
import java.util.UUID;

public class RegistrationNumberGenerator {
    
    public static String generateId(ProfileType type, String department) {
        String year = String.valueOf(Year.now().getValue());
        String dept = (department != null && !department.isEmpty()) ? department.toUpperCase().substring(0, Math.min(3, department.length())) : "GEN";
        String prefix = type == ProfileType.STUDENT ? "ST" : (type == ProfileType.EMPLOYEE ? "EM" : "US");
        String uuidPart = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        
        return String.format("%s-%s-%s-%s", year, prefix, dept, uuidPart);
    }
}
