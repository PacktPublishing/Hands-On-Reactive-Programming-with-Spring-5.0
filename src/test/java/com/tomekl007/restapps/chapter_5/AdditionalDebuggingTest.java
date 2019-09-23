package com.tomekl007.restapps.chapter_5;

import com.tomekl007.restapps.chapter_5.utils.Foo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.util.Arrays;

import static com.tomekl007.restapps.chapter_5.utils.FooNameHelper.concatAndSubstringFooName;
import static com.tomekl007.restapps.chapter_5.utils.FooNameHelper.substringFooName;
import static com.tomekl007.restapps.chapter_5.utils.FooQuantityHelper.processFooReducingQuantity;
import static com.tomekl007.restapps.chapter_5.utils.FooReporter.reportResult;

public class AdditionalDebuggingTest {


  @Test
  public void should_enable_additional_debugging_using_do_on_error() {
    Flux<Foo> flux = Flux.fromIterable(
        Arrays.asList(new Foo(1, "v", 10)));

    flux = concatAndSubstringFooName(flux);
    flux = concatAndSubstringFooName(flux);
    flux = substringFooName(flux);
    flux = processFooReducingQuantity(flux);
    flux = processFooReducingQuantity(flux);
    flux = processFooReducingQuantity(flux);
    flux = reportResult(flux, "ONE w/ EH");
    flux = flux.doOnError(error -> System.out.println(String.format("doOnError - with Error Handling failed! %s", error)));
    flux.subscribe();
  }
}

