package com.microservices.Job.Job;

import com.microservices.Job.DTO.JobWithCompanyDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface JobService {


    List<JobWithCompanyDTO> getAllJobs();

    JobDTO createJob(JobDTO jobDTO);

    JobWithCompanyDTO getJobById(Long id);

    Void deleteJobById(Long id);

    JobDTO updateJobById(Long id, JobDTO jobDTO);

    JobDTO updateJobValueById(Long id, Map<String, Object> dto);
}
