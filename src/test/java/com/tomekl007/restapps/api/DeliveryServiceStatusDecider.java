package com.tomekl007.restapps.api;

import org.springframework.stereotype.Component;

@Component
public class DeliveryServiceStatusDecider {
  public DeliveryStatus statusToReturn(){
    return DeliveryStatus.FAILED;
  }
}
