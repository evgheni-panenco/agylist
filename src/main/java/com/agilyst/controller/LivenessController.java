package com.agilyst.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.LivenessState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(LivenessController.ROOT)
public class LivenessController {

  private final ApplicationAvailability availability;

  public static final String ROOT = "/liveness";

  @GetMapping
  public Map<String, LivenessState> liveness() {
    return Map.of("state", availability.getLivenessState());
  }
}
