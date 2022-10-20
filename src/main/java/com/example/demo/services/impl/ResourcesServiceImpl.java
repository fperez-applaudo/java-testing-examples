package com.example.demo.services.impl;

import com.example.demo.models.dto.ResourcesDto;
import com.example.demo.services.ResourcesService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {

  private final RestTemplate restTemplate;

  private String apiUrl;

  private String getPath;

  @Override
  public String foo() {
    return "";
  }

  @Override
  @Value("${dummy.api.url}")
  public final void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  @Override
  @Value("${dummy.api.resources.get}")
  public final void setGetPath(String getPath) {
    this.getPath = getPath;
  }

  @Override
  public ResourcesDto getResourcesRequest() {
    try {
      ResponseEntity<ResourcesDto> entity = restTemplate.getForEntity(
          this.getEndpoint(getPath), ResourcesDto.class
      );
      return entity.getBody();
    } catch (RestClientResponseException ex) {
      log.error("Error when requesting data", ex);
      // pass through
      throw ex;
    }
  }

  private String getEndpoint(String path) {
    return apiUrl + path;
  }
}
