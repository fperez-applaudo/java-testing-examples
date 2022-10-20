package com.example.demo.controllers;

import com.example.demo.models.dto.ResourcesDto;
import com.example.demo.services.ResourcesService;
import com.example.demo.utils.ResourceFactory;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ResourcesControllerTest {

  @MockBean
  private ResourcesService resourcesService;

  @Autowired
  private WebApplicationContext ctx;

  public MockMvc mockMvc;

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
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

    this.mockMvc.perform(get("/resources")).andExpect(status().isOk()).andDo(print());
  }
}
