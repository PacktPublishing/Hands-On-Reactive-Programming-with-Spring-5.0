package com.tomekl007.restapps.chapter_6;

import com.tomekl007.restapps.domain.DeliveryItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliveryServiceRepositoryIntegrationTest {
  private static final DeliveryItem ITEM = new DeliveryItem("item", 1L);

  @Autowired
  private DeliveryServiceRepository deliveryServiceRepository;

  @Before
  public void cleanBefore(){
    deliveryServiceRepository.delete(ITEM);
  }

  @After
  public void cleanAfter(){
    deliveryServiceRepository.delete(ITEM);
  }

  @Test
  public void should_save_delivery_item_and_retrieve_by_item_name(){
    // given

    // when
    deliveryServiceRepository.save(ITEM);
    List<DeliveryItem> itemRetrievedFromDb = deliveryServiceRepository.findByItemName("item");

    // then
    assertThat(itemRetrievedFromDb.size()).isEqualTo(1);
    assertThat(itemRetrievedFromDb.get(0)).isEqualTo(ITEM);

  }
}