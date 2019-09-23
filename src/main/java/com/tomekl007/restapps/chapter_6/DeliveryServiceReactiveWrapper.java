package com.tomekl007.restapps.chapter_6;

import com.tomekl007.restapps.domain.DeliveryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DeliveryServiceReactiveWrapper {
  private DeliveryServiceRepository deliveryServiceRepository;

  @Autowired
  public DeliveryServiceReactiveWrapper(DeliveryServiceRepository deliveryServiceRepository) {

    this.deliveryServiceRepository = deliveryServiceRepository;
  }

  public Mono<DeliveryItem> save(DeliveryItem deliveryItem) {
    return Mono.just(deliveryItem).map(d -> deliveryServiceRepository.save(d));
  }

  public Flux<DeliveryItem> getByItemName(String itemName) {
    return Flux.fromIterable(deliveryServiceRepository.findByItemName(itemName));
  }
}
