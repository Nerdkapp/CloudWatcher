package com.ca.infrastructure.cloud.client;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;
import com.amazonaws.services.cloudwatch.model.ListMetricsResult;
import com.ca.infrastructure.cloud.client.aws.AWSClient;
import org.junit.Before;
import org.junit.Test;

public class AWSClientIT {

  AWSClient client;

  @Before
  public void setUp() throws Exception {
    AWSCredentials credentials = new PropertiesCredentials(getClass().getClassLoader().getResourceAsStream("awsCredentials.properties"));
    client = new AWSClient(credentials);
  }

  @Test
  public void listInstances(){
    client.getInstances("sa-east-1");
  }

  @Test
  public void listRegions(){
    client.getRegions();
  }

  @Test
  public void listMetrics() throws Exception {
    ListMetricsResult result = client.getAvailableMetrics("eu-central-1", "i-70d346cc");
    System.out.println(result);

  }
  @Test
  public void getMetrics() throws Exception {
    GetMetricStatisticsResult result = client.getMetrics("eu-central-1", "i-b4d04508","CPUCreditUsage");
    System.out.println(result);
  }
}