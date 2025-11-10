package com.example.kshitiz.server.dto;

import com.example.kshitiz.server.entity.Job;

import java.time.LocalDate;
import java.util.List;

public class JobDTO {
    private long id;
    private String jobTitle;
    private String company;
    private String experience;
    private String jobType;
    private List<String> skillsRequired;
    private String salary;
    private String location;
    private Long applicants;
    private String description;
    private String responsibilities;
    private LocalDate postedOn;
    private String postedBy;

    public JobDTO() {}

    public JobDTO(long id, String jobTitle, String company, String experience, String jobType, List<String> skillsRequired, String salary, String location, Long applicants, String description, String responsibilities, LocalDate postedOn, String postedBy) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.company = company;
        this.experience = experience;
        this.jobType = jobType;
        this.skillsRequired = skillsRequired;
        this.salary = salary;
        this.location = location;
        this.applicants = applicants;
        this.description = description;
        this.responsibilities = responsibilities;
        this.postedOn = postedOn;
        this.postedBy = postedBy;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }
    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }
    public List<String> getSkillsRequired() { return skillsRequired; }
    public void setSkillsRequired(List<String> skillsRequired) { this.skillsRequired = skillsRequired; }
    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Long getApplicants() { return applicants; }
    public void setApplicants(Long applicants) { this.applicants = applicants; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getResponsibilities() { return responsibilities; }
    public void setResponsibilities(String responsibilities) { this.responsibilities = responsibilities; }
    public LocalDate getPostedOn() { return postedOn; }
    public void setPostedOn(LocalDate postedOn) { this.postedOn = postedOn; }
    public String getPostedBy() { return postedBy; }
    public void setPostedBy(String postedBy) { this.postedBy = postedBy; }

    public Job toEntity() {
        Job job = new Job();
        job.setId(this.id);
        job.setJobTitle(this.jobTitle);
        job.setCompany(this.company);
        job.setExperience(this.experience);
        job.setJobType(this.jobType);
        job.setSkillsRequired(this.skillsRequired);
        job.setSalary(this.salary);
        job.setLocation(this.location);
        job.setApplicants(this.applicants);
        job.setDescription(this.description);
        job.setPostedOn(this.postedOn);
        job.setResponsibilities(this.responsibilities);
        return job;
    }
}
