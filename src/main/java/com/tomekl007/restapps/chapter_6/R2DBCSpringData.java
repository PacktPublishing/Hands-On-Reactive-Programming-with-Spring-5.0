package com.tomekl007.restapps.chapter_6;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.function.DatabaseClient;
import reactor.core.publisher.Mono;

public class R2DBCSpringData {


  // low-level way of interaction
  @Bean
  DatabaseClient init(){
    PostgresqlConnectionFactory connectionFactory
        = new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
        .host("localhost")
        .database("items_db")
        .username("user")
        .password("pass").build());

    DatabaseClient client = DatabaseClient.create(connectionFactory);

    Mono<Integer> affectedRows = client.execute()
        .sql("UPDATE deliveryItem SET itemNme = 'PS4'")
        .fetch().rowsUpdated();
    return client;
  }


}
