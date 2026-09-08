package com.microservices.Job.Job;

import com.microservices.Job.DTO.JobWithCompanyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> getAllJobs()
    {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobWithCompanyDTO> getJobById(@PathVariable Long id)
    {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO jobDTO)
    {
        return ResponseEntity.ok(jobService.createJob(jobDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobById(@PathVariable Long id)
    {
        jobService.deleteJobById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJobById(@PathVariable Long id, @RequestBody JobDTO jobDTO)
    {
        return ResponseEntity.ok(jobService.updateJobById(id,jobDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JobDTO> updateJobValueId(@PathVariable Long id, @RequestBody Map<String,Object> dto)
    {
        return ResponseEntity.ok(jobService.updateJobValueById(id,dto));
    }
}
