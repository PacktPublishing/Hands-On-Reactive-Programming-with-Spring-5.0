package com.tomekl007.restapps.chapter_6;

import com.tomekl007.restapps.domain.DeliveryItem;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;

public class StreamRouterConfigTest {

  @Test
  public void should_create_stream_of_deliveries() {
    // given
    DeliveryItem deliveryItem = new DeliveryItem(1L, "i1", 100L);

    DeliveryServiceRepository deliveryRepo = Mockito.mock(DeliveryServiceRepository.class);
    // when
    WebTestClient testClient = WebTestClient.bindToRouterFunction(
        new StreamItemsRouterConfig(new DeliveryServiceReactiveWrapper(deliveryRepo)).getAndPost()
    ).build();

    Mockito.when(deliveryRepo.save(any())).thenReturn(deliveryItem);
    // then
    testClient.post().uri("/stream/delivery")
        .body(Mono.just(deliveryItem), DeliveryItem.class)
        .exchange()
        .expectStatus()
        .isOk();

    Mockito.verify(deliveryRepo).save(deliveryItem);

  }

  @Test
  public void should_retrieve_stream_of_deliveries() {
    // given
    DeliveryItem deliveryItem = new DeliveryItem(1L, "i1", 100L);

    DeliveryServiceRepository deliveryRepo = Mockito.mock(DeliveryServiceRepository.class);
    // when
    WebTestClient testClient = WebTestClient.bindToRouterFunction(
        new StreamItemsRouterConfig(new DeliveryServiceReactiveWrapper(deliveryRepo)).getAndPost()
    ).build();

    Mockito.when(deliveryRepo.findByItemName("i1")).thenReturn(Collections.singletonList(deliveryItem));
    // then
    testClient.get().uri("/stream/delivery/i1")
        .exchange()
        .expectStatus()
        .isOk();
  }


}