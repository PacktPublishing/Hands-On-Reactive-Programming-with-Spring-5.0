package com.tomekl007.restapps.api;

import com.tomekl007.restapps.domain.DeliveryItemDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DeliveryControllerIntegrationTest {

  // Inject which port we was assigned
  @Value("${local.server.port}")
  private int port;

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
        .postForEntity(createTestUrl("/delivery"),
            entity, DeliveryStatus.class);

    //then
    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(response.getBody()).isEqualTo(DeliveryStatus.SUCCESS);

    //when
    List<DeliveryItemDto> getResponse = restTemplate.exchange(
        createTestUrl("/delivery/" + deliveryDto.getItemName()),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<DeliveryItemDto>>() {
        }).getBody();

    //then
    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(Objects.requireNonNull(getResponse).size()).isGreaterThan(0);
  }


  private String createTestUrl(String suffix) {
    return "http://localhost:" + port + suffix;
  }

}