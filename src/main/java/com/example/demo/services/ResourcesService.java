package com.example.demo.services;

import com.example.demo.models.dto.ResourcesDto;

public interface ResourcesService {

  String foo();

  void setApiUrl(String apiUrl);

  void setGetPath(String getPath);

  ResourcesDto getResourcesRequest();

}
