package com.example.demo.controllers;

import com.example.demo.models.dto.ResourcesDto;
import com.example.demo.services.ResourcesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourcesController {

  private final ResourcesService resourcesService;

  @GetMapping
  public ResponseEntity<ResourcesDto> getAllResources() {
    return ResponseEntity.ok(resourcesService.getResourcesRequest());
  }
}
