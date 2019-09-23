package com.tomekl007.restapps.domain;

import java.util.Objects;

public class DeliveryItemDto {
  private String itemName;
  private Long quantity;

  public DeliveryItemDto(String itemName, Long quantity) {
    this.itemName = itemName;
    this.quantity = quantity;
  }

  public DeliveryItemDto() {
  }


  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "DeliveryItemDto{" +
        "itemName='" + itemName + '\'' +
        ", quantity=" + quantity +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DeliveryItemDto that = (DeliveryItemDto) o;
    return Objects.equals(itemName, that.itemName) &&
        Objects.equals(quantity, that.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemName, quantity);
  }
}
