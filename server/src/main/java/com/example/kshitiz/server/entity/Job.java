package com.example.kshitiz.server.entity;

import com.example.kshitiz.server.dto.JobDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   @NotNull(message = "Company cannot be null or empty")
    private String company;
    @NotNull(message = "Title cannot be null or empty")
    private String jobTitle;
    @NotNull(message = "Experience cannot be null or empty")
    private String experience;
    @NotNull(message = "Job Type cannot be null or empty")
    private String jobType;
    @ElementCollection
    @NotNull(message = "Skills cannot be null or empty")
    @Size(min = 1 ,message = "Skills cannot be empty")
    private List<String> skillsRequired=new ArrayList<>();
    @NotNull(message = "Salary cannot be null or empty")
    private String salary;
    @NotNull(message = "Location cannot be null or empty")
    private String location;
    @NotNull(message = "No. of applicants can't be null or empty")
    private Long applicants;
    @NotNull(message = "Description can't be null or empty")
    private String description;
    @NotNull(message = "No. of days posted ago can't be null or empty")
    private LocalDate postedOn;
    private String responsibilities;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User postedBy;

    public Job() {}

    public Job(long id, String company, String jobTitle, String experience, String jobType, List<String> skillsRequired, String salary, String location, Long applicants, String description, LocalDate postedOn, String responsibilities, User postedBy) {
        this.id = id;
        this.company = company;
        this.jobTitle = jobTitle;
        this.experience = experience;
        this.jobType = jobType;
        this.skillsRequired = skillsRequired;
        this.salary = salary;
        this.location = location;
        this.applicants = applicants;
        this.description = description;
        this.postedOn = postedOn;
        this.responsibilities = responsibilities;
        this.postedBy = postedBy;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
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
    public LocalDate getPostedOn() { return postedOn; }
    public void setPostedOn(LocalDate postedOn) { this.postedOn = postedOn; }
    public String getResponsibilities() { return responsibilities; }
    public void setResponsibilities(String responsibilities) { this.responsibilities = responsibilities; }
    public User getPostedBy() { return postedBy; }
    public void setPostedBy(User postedBy) { this.postedBy = postedBy; }




    public JobDTO toDTO() {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(this.id);
        jobDTO.setJobTitle(this.jobTitle);
        jobDTO.setCompany(this.company);
        jobDTO.setExperience(this.experience);
        jobDTO.setJobType(this.jobType);
        jobDTO.setSkillsRequired(this.skillsRequired);
        jobDTO.setSalary(this.salary);
        jobDTO.setLocation(this.location);
        jobDTO.setApplicants(this.applicants);
        jobDTO.setDescription(this.description);
        jobDTO.setPostedOn(this.postedOn);
        jobDTO.setResponsibilities(this.responsibilities);
        jobDTO.setPostedBy(this.postedBy.getName());
        return jobDTO;
    }
}
