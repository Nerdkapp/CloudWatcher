package com.ca.infrastructure.cloud.client;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
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
}