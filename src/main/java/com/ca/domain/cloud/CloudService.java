package com.ca.domain.cloud;

import com.amazonaws.services.cloudwatch.model.Datapoint;
import com.amazonaws.services.cloudwatch.model.Metric;
import com.amazonaws.services.ec2.model.Instance;

import java.util.List;
import java.util.Map;

public interface CloudService {
  List<TaggedInstances> getInstancesGroupedByTags(String region);
  List<Datapoint> getMetricDatapoints(String region, String instance, String metric);
  List<Metric> getAvailableMetrics(String region, String instance);
}
