package com.example.springsecuritybatchjpa.controllers;

import com.example.springsecuritybatchjpa.repository.PersonRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  @Autowired
  JobLauncher jobLauncher;

  @Autowired
  PersonRepository personRepository;

  @Autowired
  Job importUserJob;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() throws Exception{

    // Clean old data from person table -2
    personRepository.deleteAll();

    JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
            .toJobParameters();
    jobLauncher.run(importUserJob,jobParameters);

    return "Admin Board: ***** Batch job has been invoked *******";
  }

}
