package com.example.kshitiz.server.services;

import com.example.kshitiz.server.entity.Profile;
import com.example.kshitiz.server.entity.User;
import com.example.kshitiz.server.repositories.ProfileRepository;
import com.example.kshitiz.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfile(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile getProfileByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;
        return profileRepository.findByUser(user);
    }
}


