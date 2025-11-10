package com.example.kshitiz.server.entity;

import com.example.kshitiz.server.dto.ApplicationDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="applicants")
public class Applicant {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @NotNull(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Email cannot be empty")
    @Email(message = "Email is not valid")
    private String email;
    @NotNull(message = "Phone number cannot be empty")
    @Size(min=10)
    private String phone;
    @NotNull(message = "Resume cannot be empty")
    private String resume;
    @NotNull(message = "Portfolio cannot be empty")
    private String portfolio;
    private String linkedIn;
    private String coverLetter;

    @ManyToOne
    @JoinColumn(name = "job_id",nullable = false)
    private Job job;

    public Applicant() {}

    public Applicant(long id, String name, String email, String phone, String resume, String portfolio, String linkedIn, String coverLetter, Job job) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.resume = resume;
        this.portfolio = portfolio;
        this.linkedIn = linkedIn;
        this.coverLetter = coverLetter;
        this.job = job;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }
    public String getPortfolio() { return portfolio; }
    public void setPortfolio(String portfolio) { this.portfolio = portfolio; }
    public String getLinkedIn() { return linkedIn; }
    public void setLinkedIn(String linkedIn) { this.linkedIn = linkedIn; }
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public ApplicationDTO toDTO(){
        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        dto.setPhone(phone);
        dto.setResume(resume);
        dto.setPortfolio(portfolio);
        dto.setLinkedIn(linkedIn);
        dto.setCoverLetter(coverLetter);
        dto.setJobId(this.job!=null?job.getId():null);
        return dto;
    }



}
