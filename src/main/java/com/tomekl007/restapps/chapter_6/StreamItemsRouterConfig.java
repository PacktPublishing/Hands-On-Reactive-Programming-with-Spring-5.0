package com.tomekl007.restapps.chapter_6;

import com.tomekl007.restapps.domain.DeliveryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class StreamItemsRouterConfig {
  private final DeliveryServiceReactiveWrapper deliveryServiceRepository;

  @Autowired
  public StreamItemsRouterConfig(DeliveryServiceReactiveWrapper deliveryServiceRepository) {
    this.deliveryServiceRepository = deliveryServiceRepository;
  }

  @Bean
  public RouterFunction<ServerResponse> getAndPost() {
    return route(POST("/stream/delivery"), this::postDelivery)
        .andRoute(GET("/stream/delivery/{itemName}"), this::getDelivery);
  }

  private Mono<ServerResponse> getDelivery(ServerRequest serverRequest) {
    String itemName = serverRequest.pathVariable("itemName");
    return ServerResponse.ok().body(deliveryServiceRepository.getByItemName(itemName), DeliveryItem.class);
  }

  public Mono<ServerResponse> postDelivery(ServerRequest request) {
    Mono<DeliveryItem> delivery = request.bodyToMono(DeliveryItem.class);
    Mono<DeliveryItem> deliveryItemSaved = delivery.flatMap(deliveryServiceRepository::save);
    return ServerResponse.ok().body(deliveryItemSaved, DeliveryItem.class);
  }

}
