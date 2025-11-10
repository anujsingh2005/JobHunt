package com.example.kshitiz.server.dto;

import com.example.kshitiz.server.entity.Experience;
import com.example.kshitiz.server.entity.Profile;

import java.util.List;

public class ProfileDTO {
    private Long id;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private String about;
    private List<String> skills;
    private List<Experience> experiences;

    public ProfileDTO() {}

    public ProfileDTO(Long id, String email, String jobTitle, String company, String location, String about, List<String> skills, List<Experience> experiences) {
        this.id = id;
        this.email = email;
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.about = about;
        this.skills = skills;
        this.experiences = experiences;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    public List<Experience> getExperiences() { return experiences; }
    public void setExperiences(List<Experience> experiences) { this.experiences = experiences; }

    public Profile toEntity() {
        Profile profile = new Profile();
        profile.setId(this.id);
        profile.setEmail(this.email);
        profile.setJobTitle(this.jobTitle);
        profile.setCompany(this.company);
        profile.setLocation(this.location);
        profile.setAbout(this.about);
        profile.setSkills(this.skills);
        profile.setExperiences(this.experiences);
        return profile;
    }
}
