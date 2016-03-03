package com.ca.infrastructure.cloud.client.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AWSClient {

  private AWSCredentials awsCredentials;

  @Autowired
  public AWSClient(AWSCredentials awsCredentials) {
    this.awsCredentials = awsCredentials;
  }

  private AmazonEC2Client newClient(){
    return new AmazonEC2Client(awsCredentials);
  }

  private AmazonEC2Client newClient(String region){
    AmazonEC2Client client = new AmazonEC2Client(awsCredentials);
    client.setRegion(RegionUtils.getRegion(region));
    return client;
  }

  public List<com.amazonaws.services.ec2.model.Region> getRegions(){
    DescribeRegionsResult result = newClient().describeRegions();
    return result.getRegions();
  }

  public List<Instance> getInstances(String region) {
    AmazonEC2Client client = newClient(region);
    DescribeInstancesResult result = client.describeInstances();

    List<Instance> instances = new ArrayList<>();

    for (Reservation reservation : result.getReservations()) {
      instances.addAll(reservation.getInstances());
    }
    return instances;
  }
}
