package com.ca.infrastructure.cloud.client.aws;

import com.amazonaws.services.ec2.model.Tag;
import com.ca.domain.cloud.CloudService;
import com.ca.domain.cloud.TaggedInstances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AWSCloudService implements CloudService {

  private AWSClient client;

  @Autowired
  public AWSCloudService(AWSClient client) {
    this.client = client;
  }

  @Override
  public List<TaggedInstances> getInstancesGroupedByTags(String region) {
    return client.getInstances(region).stream()
        .collect(Collectors.groupingBy(i -> getNormalizedTags(i.getTags())))
        .entrySet().stream().map(stringListEntry -> new TaggedInstances(stringListEntry.getKey(), stringListEntry.getValue())).collect(Collectors.toList());
  }

  private String getNormalizedTags(List<Tag> tags){

    return tags
        .stream()
        .sorted((p1, p2) -> p1.getKey().compareTo(p2.getKey()))
        .map(p -> p.getKey() + ":" + p.getValue())
        .collect(Collectors.joining(","));
  }
}
