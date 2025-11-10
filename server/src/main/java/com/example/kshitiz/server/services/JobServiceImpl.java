package com.example.kshitiz.server.services;

import com.example.kshitiz.server.entity.Job;
import com.example.kshitiz.server.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job getJob(Long id) {
        if (id == null) return null;
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getJobsByUser(Long userId) {
        return jobRepository.findByPostedById(userId);
    }

    @Override
    public List<Job> searchJobs(String title, String location, String experience, String jobType) {
        List<Job> all = jobRepository.findAll();
        return all.stream().filter(j ->
            (title == null || j.getJobTitle().toLowerCase().contains(title.toLowerCase())) &&
            (location == null || Objects.equals(j.getLocation(), location) || j.getLocation().toLowerCase().contains(location.toLowerCase())) &&
            (experience == null || Objects.equals(j.getExperience(), experience) || j.getExperience().toLowerCase().contains(experience.toLowerCase())) &&
            (jobType == null || Objects.equals(j.getJobType(), jobType) || j.getJobType().toLowerCase().contains(jobType.toLowerCase()))
        ).collect(Collectors.toList());
    }
}


