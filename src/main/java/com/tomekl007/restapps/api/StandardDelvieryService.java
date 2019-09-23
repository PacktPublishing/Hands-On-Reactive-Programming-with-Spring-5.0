package com.tomekl007.restapps.api;

import com.tomekl007.restapps.domain.DeliveryItem;
import com.tomekl007.restapps.domain.DeliveryItemDto;
import com.tomekl007.restapps.chapter_6.DeliveryServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("!integration")
public class StandardDelvieryService implements DeliveryService {

  private DeliveryServiceRepository deliveryServiceRepository;

  @Autowired
  public StandardDelvieryService(DeliveryServiceRepository deliveryServiceRepository) {
    this.deliveryServiceRepository = deliveryServiceRepository;
  }

  @Override
  public DeliveryStatus deliver(DeliveryItemDto deliveryItemDto) {
    DeliveryItem deliveryItem = new DeliveryItem(deliveryItemDto.getItemName(), deliveryItemDto.getQuantity());

    System.out.println(
        String.format("Delivering item: %s with quantity: %d",
            deliveryItem.getItemName(), deliveryItem.getQuantity())
    );
    DeliveryItem save = deliveryServiceRepository.save(deliveryItem);
    if (save != null) {
      return DeliveryStatus.SUCCESS;
    } else {
      return DeliveryStatus.FAILED;
    }
  }

  @Override
  public List<DeliveryItemDto> loadByItemName(String itemName) {
    List<DeliveryItem> byItemName = deliveryServiceRepository.findByItemName(itemName);
    return byItemName.stream()
        .map(e -> new DeliveryItemDto(e.getItemName(), e.getQuantity()))
        .collect(Collectors.toList());
  }
}
