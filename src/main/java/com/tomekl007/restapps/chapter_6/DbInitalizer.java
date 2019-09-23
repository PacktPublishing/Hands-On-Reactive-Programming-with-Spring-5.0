package com.tomekl007.restapps.chapter_6;

import com.tomekl007.restapps.domain.DeliveryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;

@Component
public class DbInitalizer {
  private DeliveryServiceReactiveWrapper deliveryServiceReactiveWrapper;

  @Autowired
  public DbInitalizer(DeliveryServiceReactiveWrapper deliveryServiceReactiveWrapper) {
    this.deliveryServiceReactiveWrapper = deliveryServiceReactiveWrapper;
  }

  @PostConstruct
  public void init() {
    Flux<DeliveryItem> itemsToInsert = Flux.range(0, 1000).map(v ->
        new DeliveryItem("item_" + v, v * 10L))
        .flatMap(deliveryServiceReactiveWrapper::save)
        .subscribeOn(Schedulers.parallel()); // we want insert to be executed in parallel

  }
}
