package com.tomekl007.restapps.api;

import com.tomekl007.restapps.domain.DeliveryItem;
import com.tomekl007.restapps.domain.DeliveryItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Profile("integration")
public class MockDeliveryService implements DeliveryService{

  private DeliveryServiceStatusDecider deliveryServiceStatusDecider;

  @Autowired
  public MockDeliveryService(DeliveryServiceStatusDecider deliveryServiceStatusDecider) {
    this.deliveryServiceStatusDecider = deliveryServiceStatusDecider;
  }

  @Override
  public DeliveryStatus deliver(DeliveryItemDto deliveryItemDto) {
    return deliveryServiceStatusDecider.statusToReturn();
  }

  @Override
  public List<DeliveryItemDto> loadByItemName(String itemName) {
    return Collections.emptyList();
  }
}
