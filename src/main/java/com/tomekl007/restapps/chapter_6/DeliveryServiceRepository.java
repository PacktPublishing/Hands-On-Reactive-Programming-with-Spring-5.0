package com.tomekl007.restapps.chapter_6;

import com.tomekl007.restapps.domain.DeliveryItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryServiceRepository extends CrudRepository<DeliveryItem, Long> {

  @Query("select t from DeliveryItem t where t.itemName =:itemName")
  List<DeliveryItem> findByItemName(@Param("itemName") String itemName);
}