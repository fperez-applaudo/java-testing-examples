package com.example.demo.services.impl;

import com.example.demo.models.dto.ResourcesDto;
import com.example.demo.services.ResourcesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {

  private final RestTemplate restTemplate;
  private static final String API_URL = "https://dummyjson.com/";
  private static final String GET_PATH = "products";

  @Override
  public String foo() {
    return "";
  }

  @Override
  public ResourcesDto getResourcesRequest() {
    try {
      ResponseEntity<ResourcesDto> entity = restTemplate.getForEntity(
          this.getEndpoint(GET_PATH), ResourcesDto.class
      );
      return entity.getBody();
    } catch (RestClientResponseException ex) {
      log.error("Error when requesting data", ex);
      // pass through
      throw ex;
    }
  }

  private String getEndpoint(String path) {
    return API_URL + path;
  }
}
