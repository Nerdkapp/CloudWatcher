package com.ca.infrastructure.cloud.client.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.*;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class AWSClient {

  private AWSCredentials awsCredentials;

  @Autowired
  public AWSClient(AWSCredentials awsCredentials) {
    this.awsCredentials = awsCredentials;
  }

  private AmazonEC2Client newAmazonClient(){
    return new AmazonEC2Client(awsCredentials);
  }

  private AmazonEC2Client newAmazonClient(String region){
    AmazonEC2Client client = new AmazonEC2Client(awsCredentials);
    client.setRegion(RegionUtils.getRegion(region));
    return client;
  }

  private AmazonCloudWatchClient newCloudWatchCLient(String region){
    AmazonCloudWatchClient client = new AmazonCloudWatchClient(awsCredentials);
    client.setRegion(RegionUtils.getRegion(region));

    return client;
  }


  public List<com.amazonaws.services.ec2.model.Region> getRegions(){
    DescribeRegionsResult result = newAmazonClient().describeRegions();
    return result.getRegions();
  }

  public List<Instance> getInstances(String region) {
    AmazonEC2Client client = newAmazonClient(region);
    DescribeInstancesResult result = client.describeInstances();

    List<Instance> instances = new ArrayList<>();

    for (Reservation reservation : result.getReservations()) {
      instances.addAll(reservation.getInstances());
    }
    return instances;
  }

  public ListMetricsResult listMetrics(String region){

    return newCloudWatchCLient(region).listMetrics();
  }

  public GetMetricStatisticsResult getMetrics(String region, String name){
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(Calendar.HOUR, -1);
    Date oneHourBack = cal.getTime();

    return newCloudWatchCLient(region).getMetricStatistics(new GetMetricStatisticsRequest().
            withDimensions(new Dimension().withName("InstanceId").withValue(name)).
            withMetricName("CPUUtilization").
            withEndTime(new Date()).
            withStartTime(oneHourBack).
            withPeriod(60).
            withStatistics(new Statistic[]{Statistic.SampleCount}).
            withNamespace("AWS/EC2"));
  }
}
