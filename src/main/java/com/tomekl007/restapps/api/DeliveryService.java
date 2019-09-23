package com.tomekl007.restapps.api;

import com.tomekl007.restapps.domain.DeliveryItemDto;

import java.util.List;

public interface DeliveryService {
  DeliveryStatus deliver(DeliveryItemDto deliveryItemDto);

  List<DeliveryItemDto> loadByItemName(String itemName);
}
