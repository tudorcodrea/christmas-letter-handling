package com.christmas.letter.sender.controller;

import com.christmas.letter.sender.service.SnsSubscriberManagerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriber")
public class SubscriberSnsSqsController {

  @Autowired
  private SnsSubscriberManagerService subscriberManagerService;

  @PostMapping("/add")
  public ResponseEntity<Object> addNewSubscriber(@NotNull @RequestBody String channelName) {

    subscriberManagerService.addCommunicationChannel(channelName);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/remove")
  public ResponseEntity<Object> removeSubscriber(@NotNull @RequestBody String channelName) {

    subscriberManagerService.removeCommunicationChannel(channelName);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

//  @Autowired
//  private JobLauncher jobLauncher;
//
//  @Autowired
//  private JobSchedulerComponent jobCreator;

//  @RequestMapping("/launchjob")
//  public String handle() throws Exception {
//    try {
//      Job job = jobCreator.createJob(... params ...);
//      JobParameters
//          jobParameters = new JobParametersBuilder().addLong("time", new Date().getTime()).toJobParameters();
//      jobLauncher.run(job, jobParameters);
//    } catch (Exception e) {
//
//    }
//
//    return "Done";
//  }

}
