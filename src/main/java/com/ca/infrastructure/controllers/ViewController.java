package com.ca.infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
  @RequestMapping("/")
  String home() {
    return "index";
  }

  @RequestMapping("/instanceDetails/{region}/{instanceId}")
  String instanceDetails(@PathVariable String region, @PathVariable String instanceId, Model model ) {
    model.addAttribute("region", region);
    model.addAttribute("instanceId", instanceId);
    return "instanceDetails";
  }
}
