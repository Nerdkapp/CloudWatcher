package com.ca.domain.cloud;

import com.amazonaws.services.ec2.model.Instance;

import java.util.List;

public class TaggedInstances {
  private String tags;
  private List<Instance> instances;

  public TaggedInstances(String tags, List<Instance> instances) {
    this.tags = tags;
    this.instances = instances;
  }

  public String getTags() {
    return tags;
  }

  public List<Instance> getInstances() {
    return instances;
  }
}
