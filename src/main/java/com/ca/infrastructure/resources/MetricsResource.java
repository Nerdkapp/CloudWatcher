package com.ca.infrastructure.resources;

import com.amazonaws.services.cloudwatch.model.Datapoint;
import com.amazonaws.services.cloudwatch.model.Metric;
import com.ca.infrastructure.cloud.client.aws.AWSCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MetricsResource {
  AWSCloudService cloudService;

  @Autowired
  public MetricsResource(AWSCloudService cloudService) {
    this.cloudService = cloudService;
  }

  @RequestMapping(value = "/metrics/{region}/{instance}/{metric}", method = RequestMethod.GET)
  public @ResponseBody
  List<Datapoint> getMetric(@PathVariable String region, @PathVariable String instance, @PathVariable String metric){
    return cloudService.getMetricDatapoints(region, instance, metric);
  }

  @RequestMapping(value = "/metrics/list/{region}/{instance}", method = RequestMethod.GET)
  public @ResponseBody
  List<Metric> getMetricsList(@PathVariable String region, @PathVariable String instance){
    return cloudService.getAvailableMetrics(region, instance);
  }
}
