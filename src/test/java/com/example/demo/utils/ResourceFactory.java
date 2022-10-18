package com.example.demo.utils;

import com.example.demo.models.dto.ResourcesDto;
import net.datafaker.Faker;

import java.util.List;

public abstract class ResourceFactory {

  private static Faker faker = new Faker();

  public static ResourcesDto createResourcesDto() {
    return createResourcesDto(0);
  }

  public static ResourcesDto createResourcesDto(int qt) {
    return ResourcesDto.builder()
        .products(createProducts(qt))
        .total(faker.number().positive())
        .skip(faker.number().positive())
        .limit(faker.number().positive())
        .build();
  }

  public static ResourcesDto.Product[] createProducts(int qt) {
    qt = qt == 0 ? faker.number().numberBetween(1, 10) : qt;
    ResourcesDto.Product[] products = new ResourcesDto.Product[qt];
    for (int i = 0; i < products.length; i++)
      products[i] = createProduct();
    return products;
  }

  public static ResourcesDto.Product createProduct() {
    return ResourcesDto.Product.builder()
        .id(faker.number().positive())
        .title(faker.famousLastWords().lastWords())
        .description(faker.lorem().characters())
        .price(faker.number().positive())
        .discountPercentage(faker.number().randomDouble(2,0, 1))
        .rating(faker.number().randomDouble(2, 0, 10))
        .stock(faker.number().positive())
        .brand(faker.breakingBad().character())
        .category(faker.starTrek().species())
        .thumbnail(faker.internet().url())
        .images(createImages())
        .build();
  }

  public static List<String> createImages() {
    return List.of(faker.internet().url(), faker.internet().url(), faker.internet().url());
  }
}
