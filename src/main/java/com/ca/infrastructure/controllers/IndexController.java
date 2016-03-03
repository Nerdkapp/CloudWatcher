package com.ca.infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
  @RequestMapping("/")
  String home() {
    return "index";
  }

  @RequestMapping("/")
  String instanceDetails() {
    return "instanceDetails";
  }
}
