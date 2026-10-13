package com.microservices.Job.Job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.Job.Clients.CompanyClient;
import com.microservices.Job.Clients.ReviewClient;
import com.microservices.Job.DTO.JobWithCompanyDTO;
import com.microservices.Job.External.Company;
import com.microservices.Job.External.Reviews;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
//    private final RestTemplate restTemplate;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;
    public final HttpServletResponse response;
//    int attempts = 0;

    @Override
    //Use @Retry for retries if application goes down
//    @Retry(name = "companyBreaker", fallbackMethod = "companyFallback")
    //Use @RateLimiter for adding rate limit in APIs
    @RateLimiter(name = "companyBreaker")
    @CircuitBreaker(name = "companyBreaker")
    public List<JobWithCompanyDTO> getAllJobs() {
//        System.out.println("Attempts: "+ (++attempts));
        List<Job> jobs=jobRepository.findAll();
        return jobs.stream().map(job-> convertToDTO(job)).toList();
    }

//    public List<String> companyFallback(Exception e)
//    {
//        List<String> l = new ArrayList<>();
//        l.add("Dummy");
//        return l;
//    }

    @Override
    public JobDTO createJob(JobDTO jobDTO) {
        Job job=new Job();
        modelMapper.map(jobDTO,job);
        Job updatedJob=jobRepository.save(job);
        return modelMapper.map(updatedJob, JobDTO.class);
    }

    @Override
    public JobWithCompanyDTO getJobById(Long id) {
        Job job=jobRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Job with this id: "+id+" doesn't exist"));
        JobWithCompanyDTO jobWithCompanyDTO=convertToDTO(job);
        return jobWithCompanyDTO;
    }

    @Override
    public Void deleteJobById(Long id) {
        if(jobRepository.existsById(id))
        {
            jobRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Job with this id: "+id+" doesn't exist");
        }
        return null;
    }

    @Override
    public JobDTO updateJobById(Long id, JobDTO jobDTO) {
        Job job=jobRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Job with this id: "+id+" doesn't exist"));
        jobDTO.setId(id);
        modelMapper.map(jobDTO,job);
        jobRepository.save(job);
        return modelMapper.map(job, JobDTO.class);
    }


    @Override
    public JobDTO updateJobValueById(Long id, Map<String, Object> dto) {
        Job job = jobRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Job with this id: "+id+" doesn't exist"));;
        dto.forEach((key,value)->
        {
            switch (key)
            {
                case "title": job.setTitle((String) value);
                break;

                case "description": job.setDescription((String) value);
                break;

                case "maxSalary": job.setMaxSalary((String) value);
                break;

                case "minSalary": job.setMinSalary((String) value);
                break;

                case "location": job.setLocation((String) value);
                break;

                case "companyId": job.setCompanyId((Long) value);
                break;

                case "reviewId": job.setReviewId((Long) value);
                break;

                default:
                    throw new IllegalArgumentException("Invalid credentials");
            }
        });
        return modelMapper.map(job, JobDTO.class);
    }

    public JobWithCompanyDTO convertToDTO(Job job)
    {
//        Company company = restTemplate.getForObject("http://company:8081/company/"+job.getCompanyId(), Company.class);

        Company company = companyClient.getCompany(job.getCompanyId());

//        ResponseEntity<List<Reviews>> reviewResponse = restTemplate
//                .exchange("http://reviews:8083/reviews?companyId="+job.getCompanyId(), HttpMethod.GET,
//                        null, new ParameterizedTypeReference<>() {
//                        });

        List<Reviews> reviews = reviewClient.getReviews(job.getCompanyId());

        JobWithCompanyDTO jobWithCompanyDTO=modelMapper.map(job,JobWithCompanyDTO.class);
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReviews(reviews);
        return jobWithCompanyDTO;
    }
}


