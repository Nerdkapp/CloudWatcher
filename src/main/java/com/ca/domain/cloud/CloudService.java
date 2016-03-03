package com.ca.domain.cloud;

import com.amazonaws.services.ec2.model.Instance;

import java.util.List;
import java.util.Map;

public interface CloudService {
  public List<TaggedInstances> getInstancesGroupedByTags(String region);
}
