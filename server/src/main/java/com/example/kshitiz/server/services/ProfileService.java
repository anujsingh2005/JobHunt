package com.example.kshitiz.server.services;

import com.example.kshitiz.server.entity.Profile;

public interface ProfileService {
    Profile createProfile(Profile profile);
    Profile getProfile(Long id);
    void deleteProfile(Long id);
    Profile getProfileByUserId(Long userId);
}


