package com.ca.infrastructure.resources;


import com.amazonaws.services.ec2.model.Region;
import com.ca.infrastructure.cloud.client.aws.AWSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionsResource {

  private AWSClient awsClient;

  @Autowired
  public RegionsResource(AWSClient awsClient) {
    this.awsClient = awsClient;
  }

  @RequestMapping(value = "/regions", method = RequestMethod.GET)
  public @ResponseBody List<Region> getRegions(){
    return awsClient.getRegions();
  }

}
