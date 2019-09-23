package com.tomekl007.restapps.chapter_4;

import com.tomekl007.restapps.domain.DeliveryItem;
import com.tomekl007.restapps.chapter_6.DeliveryServiceRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.Mockito.when;

public class DeliveryRouterConfigTest {

  @Test
  public void should_return_delivery_items() {
    // given
    DeliveryItem[] deliveryItems = {
        testDelivery(1L), testDelivery(2L),
        testDelivery(3L), testDelivery(4L),
        testDelivery(5L), testDelivery(6L),
        testDelivery(7L), testDelivery(8L),
        testDelivery(9L), testDelivery(10L),
        testDelivery(11L), testDelivery(12L),
        testDelivery(13L), testDelivery(14L),
        testDelivery(15L), testDelivery(16L)};

    DeliveryServiceRepository deliveryRepo = Mockito.mock(DeliveryServiceRepository.class);
    when(deliveryRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(deliveryItems)));

    // when
    WebTestClient testClient = WebTestClient.bindToRouterFunction(
        new DeliveryRouterConfig(deliveryRepo).allDeliveries()
    ).build();

    // then
    testClient.get().uri("/reactive/deliveries")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$").isArray()
        .jsonPath("$").isNotEmpty()
        .jsonPath("$[0].id").isEqualTo(deliveryItems[0].getId().toString())
        .jsonPath("$[0].itemName").isEqualTo("Item 1").jsonPath("$[1].id")
        .isEqualTo(deliveryItems[1].getId().toString()).jsonPath("$[1].itemName")
        .isEqualTo("Item 2").jsonPath("$[11].id")
        .isEqualTo(deliveryItems[11].getId().toString())
        .jsonPath("$[11].itemName").isEqualTo("Item 12")
        .jsonPath("$[12]")
        .doesNotExist()
        .jsonPath("$[12]").doesNotExist();
  }

  private final AtomicLong atomicLong = new AtomicLong();

  private DeliveryItem testDelivery(Long number) {
    DeliveryItem deliveryItem = new DeliveryItem();
    deliveryItem.setId(atomicLong.incrementAndGet());
    deliveryItem.setItemName("Item " + number);
    deliveryItem.setQuantity(number);
    return deliveryItem;
  }

}