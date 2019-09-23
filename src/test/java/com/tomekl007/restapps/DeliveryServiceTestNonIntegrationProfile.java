package com.tomekl007.restapps;

import com.tomekl007.restapps.api.DeliveryService;
import com.tomekl007.restapps.api.DeliveryServiceStatusDecider;
import com.tomekl007.restapps.api.DeliveryStatus;
import com.tomekl007.restapps.domain.DeliveryItem;
import com.tomekl007.restapps.domain.DeliveryItemDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DeliveryServiceTestNonIntegrationProfile {

  @Autowired
  private DeliveryService deliveryService;

  @Test
  public void should_return_status_same_as_decider(){
    // given
    DeliveryItemDto item = new DeliveryItemDto("i1", 100L);

    // when
    DeliveryStatus response = deliveryService.deliver(item);

    // then
    assertThat(response).isEqualTo(DeliveryStatus.SUCCESS);
  }
}
