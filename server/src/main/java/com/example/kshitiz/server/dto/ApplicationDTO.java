package com.example.kshitiz.server.dto;

import com.example.kshitiz.server.entity.Applicant;

public class ApplicationDTO {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String resume;
    private String portfolio;
    private String linkedIn;
    private Long jobId;
    private String coverLetter;

    public ApplicationDTO() {}

    public ApplicationDTO(long id, String name, String email, String phone, String resume, String portfolio, String linkedIn, Long jobId, String coverLetter) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.resume = resume;
        this.portfolio = portfolio;
        this.linkedIn = linkedIn;
        this.jobId = jobId;
        this.coverLetter = coverLetter;
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
    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
    public Applicant toEntity() {
        Applicant applicant = new Applicant();
        applicant.setId(this.id);
        applicant.setName(this.name);
        applicant.setEmail(this.email);
        applicant.setPhone(this.phone);
        applicant.setResume(this.resume);
        applicant.setPortfolio(this.portfolio);
        applicant.setLinkedIn(this.linkedIn);
        applicant.setCoverLetter(this.coverLetter);
        return applicant;
    }
}
