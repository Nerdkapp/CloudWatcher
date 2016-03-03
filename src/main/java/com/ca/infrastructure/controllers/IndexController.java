package com.ca.infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
  @RequestMapping("/")
  String home() {
    return "index";
  }

  @RequestMapping("/instanceDetails/{instanceId}")
  String instanceDetails(@PathVariable String instanceId ) {
    System.out.println(instanceId);
    return "instanceDetails";
  }
}
