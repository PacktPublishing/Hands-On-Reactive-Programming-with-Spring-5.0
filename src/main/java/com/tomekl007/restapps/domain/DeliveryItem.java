package com.tomekl007.restapps.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class DeliveryItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String itemName;
  private Long quantity;

  public DeliveryItem() {
  }

  public DeliveryItem(Long id, String itemName, Long quantity) {
    this.id = id;
    this.itemName = itemName;
    this.quantity = quantity;
  }

  public DeliveryItem(String itemName, Long quantity) {
    this.itemName = itemName;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    return "DeliveryItem{" +
        "id=" + id +
        ", itemName='" + itemName + '\'' +
        ", quantity=" + quantity +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DeliveryItem that = (DeliveryItem) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(itemName, that.itemName) &&
        Objects.equals(quantity, that.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, itemName, quantity);
  }
}
