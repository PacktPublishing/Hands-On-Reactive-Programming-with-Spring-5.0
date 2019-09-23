package com.tomekl007.restapps.chapter_5;

import com.tomekl007.restapps.chapter_5.utils.Foo;
import com.tomekl007.restapps.chapter_5.utils.FooNameHelper;
import com.tomekl007.restapps.chapter_5.utils.FooQuantityHelper;
import com.tomekl007.restapps.chapter_5.utils.FooReporter;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.util.Arrays;

public class DebugStackTraceTest {

  @Test(expected = Exception.class)
  public void should_get_stack_trace_from_failure() {
    Flux<Foo> flux = Flux.fromIterable(
        Arrays.asList(new Foo(1, "v", 10)));

    flux = FooNameHelper.concatFooName(flux);
    flux = FooNameHelper.substringFooName(flux);
    flux = FooReporter.reportResult(flux);
    flux.subscribe();


    flux = FooNameHelper.substringFooName(flux);
    flux = FooQuantityHelper.divideFooQuantity(flux);
    flux.subscribe();

  }

}
