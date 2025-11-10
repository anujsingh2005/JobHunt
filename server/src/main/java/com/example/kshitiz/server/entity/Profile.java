package com.example.kshitiz.server.entity;

import com.example.kshitiz.server.dto.ProfileDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;
    private String jobTitle;
//    private String profileUrl;
    private String company;
    private String location;
    private String about;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    // Storing skills as a list of strings
    @ElementCollection
    @Size(min=1)
    private List<String> skills = new ArrayList<>();

    // Bidirectional mapping of experiences
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

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
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    public List<Experience> getExperiences() { return experiences; }
    public void setExperiences(List<Experience> experiences) { this.experiences = experiences; }

    public ProfileDTO toDTO() {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(this.id);
        dto.setEmail(this.email);
        dto.setJobTitle(this.jobTitle);
//        dto.setProfileUrl(this.profileUrl);
        dto.setCompany(this.company);
        dto.setLocation(this.location);
        dto.setAbout(this.about);
        dto.setSkills(this.skills);
        dto.setExperiences(this.experiences);
        return dto;
    }
}
