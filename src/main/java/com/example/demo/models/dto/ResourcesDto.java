package com.example.demo.models.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = ResourcesDto.ResourcesDtoBuilder.class)
public class ResourcesDto {


  Product[] products;
  int total;
  int skip;
  int limit;

  @Value
  @Builder
  @JsonDeserialize(builder = ResourcesDto.Product.ProductBuilder.class)
  public static class Product {
    int id;
    String title;
    String description;
    int price;
    double discountPercentage;
    double rating;
    int stock;
    String brand;
    String category;
    String thumbnail;
    List<String> images;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ProductBuilder { }
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class ResourcesDtoBuilder { }

}
