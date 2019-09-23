package com.tomekl007.restapps.chapter_5;

import com.tomekl007.restapps.chapter_5.utils.Foo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Arrays;

import static com.tomekl007.restapps.chapter_5.utils.FooNameHelper.concatAndSubstringFooName;
import static com.tomekl007.restapps.chapter_5.utils.FooQuantityHelper.divideFooQuantity;
import static com.tomekl007.restapps.chapter_5.utils.FooReporter.reportResult;

public class DebugLoggingTest {
  private static Logger logger = LoggerFactory.getLogger(DebugStackTraceTest.class);


  @Test
  public void should_use_checkpoint_with_logging() {
    Flux<Foo> flux = Flux.fromIterable(
        Arrays.asList(new Foo(1, "v", 10)));

    logger.info("starting processing!");

    flux = concatAndSubstringFooName(flux);
    flux = flux.checkpoint("CHECKPOINT 1");
    flux = concatAndSubstringFooName(flux);
    flux = divideFooQuantity(flux);
    flux = flux.checkpoint("CHECKPOINT 2", true);
    flux = reportResult(flux, "FOUR");
    flux = concatAndSubstringFooName(flux).doOnError(error ->
        logger.error("Approach With Checkpoint and Logging failed!", error));
    flux.subscribe();
  }
}
