package com.example.demo.controllers;

import com.example.demo.models.dto.ResourcesDto;
import com.example.demo.services.ResourcesService;
import com.example.demo.services.impl.ResourcesServiceImpl;
import com.example.demo.utils.ResourceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ResourcesControllerTest {

  @MockBean
  private ResourcesService resourcesService;

  private ResourcesService realResourcesService;

  @Autowired
  private WebApplicationContext ctx;

  @Autowired
  private ObjectMapper objectMapper;

  public MockMvc mockMvc;

  @Autowired
  private RestTemplate restTemplate;

  private MockRestServiceServer mockServer;


  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
    mockServer.reset();
    this.realResourcesService = new ResourcesServiceImpl(restTemplate);
  }

  @Test
  @SneakyThrows
  public void getAllResourcesMockServiceOk() {
    // given
    ResourcesDto resourcesDto = ResourceFactory.createResourcesDto(10);
    // when
    when(resourcesService.getResourcesRequest())
    // then
        .thenReturn(resourcesDto);

    this.mockMvc.perform(get("/resources")).andExpect(status().isOk());
  }

  @Test
  @SneakyThrows
  public void getAllResourcesMockServerOk() {
    // given
    int productQt = 10;
    ResourcesDto resourcesDto = ResourceFactory.createResourcesDto(productQt);
    String resourcesDtoRaw = objectMapper.writeValueAsString(resourcesDto);
    // when
    mockServer.expect(requestTo("https://dummyjson.com/products"))
        .andExpect(method(HttpMethod.GET))
        // then
        .andRespond(
            withSuccess(resourcesDtoRaw, MediaType.APPLICATION_JSON)
        );

    ResourcesDto responseBody = realResourcesService.getResourcesRequest();

    mockServer.verify();

    assertEquals(responseBody.getProducts().length, productQt);
  }

}
