package com.tomekl007.restapps.chapter_1;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ReactiveChallengeCallbackHell {
  @Test
  public void without_reactor_api_reactive_code_yields_callback_hell() {
    CompletableFuture
        .supplyAsync(action())
        .whenComplete(new BiConsumer<String, Throwable>() {
          @Override
          public void accept(String s, Throwable throwable) {
            if(throwable != null){
              fallbackAction();
            }else{
              CompletableFuture completableFuture = executeAsyncCall(s);
              completableFuture.whenComplete((BiConsumer) (o, o2) -> {
                // another handling !!
                // such code yields async callback hell - you have a lot of enclosed callbacks

              });
            }
          }

          private CompletableFuture executeAsyncCall(String s) {
            return CompletableFuture.supplyAsync(() -> "another async call");
          }

        });
  }

  private Supplier<String> action() {
    return () -> "some action";
  }


  private void fallbackAction() {
    System.out.println("execute some fallback");
  }

}
