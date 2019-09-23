package com.tomekl007.restapps.api;

import com.tomekl007.restapps.domain.DeliveryItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeliveryController {

  private DeliveryService deliveryService;

  @Autowired
  public DeliveryController(DeliveryService deliveryService) {
    this.deliveryService = deliveryService;
  }

  @GetMapping("/delivery/{itemName}")
  public List<DeliveryItemDto> getDeliveryItems(@PathVariable final String itemName) {
    return deliveryService.loadByItemName(itemName);
  }

  @PostMapping(value = "/delivery", consumes = "application/json")
  public DeliveryStatus deliveryItem(@RequestBody DeliveryItemDto deliveryItem) {
    return deliveryService.deliver(deliveryItem);
  }
}
