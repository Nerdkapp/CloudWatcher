package com.ca.infrastructure.resources;

import com.ca.domain.cloud.TaggedInstances;
import com.ca.infrastructure.cloud.client.aws.AWSCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstancesResource {
  AWSCloudService cloudService;

  @Autowired
  public InstancesResource(AWSCloudService cloudService) {
    this.cloudService = cloudService;
  }

  @RequestMapping(value = "/instances/{region}", method = RequestMethod.GET)
  public @ResponseBody List<TaggedInstances> getInstancesGroupedByTag(@PathVariable String region){
    return cloudService.getInstancesGroupedByTags(region);
  }


}
