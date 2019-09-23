package com.tomekl007.restapps.chapter_4;

import com.tomekl007.restapps.domain.DeliveryItem;
import com.tomekl007.restapps.chapter_6.DeliveryServiceRepository;
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
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeliveryRouterConfig {

  private final DeliveryServiceRepository deliveryServiceRepository;

  @Autowired
  public DeliveryRouterConfig(DeliveryServiceRepository deliveryServiceRepository) {
    this.deliveryServiceRepository = deliveryServiceRepository;
  }

  @Bean
  public RouterFunction<ServerResponse> allDeliveries() {
    return route(GET("/reactive/deliveries"), this::recent);
  }

  public Mono<ServerResponse> recent(ServerRequest request) {
    return ServerResponse.ok()
        .body(Flux.fromIterable(deliveryServiceRepository.findAll()).take(12), DeliveryItem.class);
  }

  public Mono<ServerResponse> postDelivery(ServerRequest request) {
    Mono<DeliveryItem> delivery = request.bodyToMono(DeliveryItem.class);
    Mono<DeliveryItem> deliveryItemSaved = delivery.map(deliveryServiceRepository::save);
    return ServerResponse
        .created(URI.create(
            "http://localhost:8080/delivery/" +
                Objects.requireNonNull(deliveryItemSaved.block()).getItemName()))
        .body(deliveryItemSaved, DeliveryItem.class);
  }

}