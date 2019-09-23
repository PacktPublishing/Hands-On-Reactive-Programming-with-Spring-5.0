package com.tomekl007.restapps.api;

import com.tomekl007.restapps.domain.DeliveryItemDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

// This is anti-patter - avoid that!
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DeliveryControllerIntegrationLiveTest {


  @Ignore("Live test with hard-coded port is an anti-pattern. For proper test see DeliveryControllerIntegrationTest")
  @Test
  public void should_create_and_retrieve_delivery() {
    //given
    RestTemplate restTemplate = new RestTemplate();
    DeliveryItemDto deliveryDto = new DeliveryItemDto("123", 200L);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<DeliveryItemDto> entity = new HttpEntity<>(deliveryDto, httpHeaders);

    //when
    ResponseEntity<DeliveryStatus> response = restTemplate
        .postForEntity("http://localhost:8080/delivery",
            entity, DeliveryStatus.class);

    //then
    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(response.getBody()).isEqualTo(DeliveryStatus.SUCCESS);

    //when
    List<DeliveryItemDto> getResponse = restTemplate.exchange(
        "http://localhost:8080/delivery/" +
            deliveryDto.getItemName(),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<DeliveryItemDto>>() {
        }).getBody();

    //then
    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(Objects.requireNonNull(getResponse).size()).isGreaterThan(0);
  }


}