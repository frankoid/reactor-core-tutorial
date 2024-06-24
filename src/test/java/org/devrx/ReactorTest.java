package org.devrx;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

@Slf4j
public class ReactorTest {

  @Test
  public void simpleSubscribe() {
    List<Integer> elements = new ArrayList<>();
    List<Integer> elements2 = new ArrayList<>();

    Flux<Integer> flux = Flux.just(1, 2, 3, 4)
        .log()
        .map(i -> {
          log.debug("{}:{}", i, Thread.currentThread());
          return i * 2;
        });
    flux.subscribe(new Subscriber<>() {
      private Subscription s;
      long onNextAmount;

      @Override
      public void onSubscribe(Subscription s) {
        this.s = s;
        s.request(2);
      }

      @Override
      public void onNext(Integer integer) {
        elements.add(integer);
        onNextAmount++;
        if (onNextAmount % 2 == 0) {
          s.request(2);
        }
      }

      @Override
      public void onError(Throwable t) {
      }

      @Override
      public void onComplete() {
      }
    });
    log.info("After subscribe(elements)");
    flux.subscribe(elements2::add);
    log.info("After subscribe(elements2::add)");

    assertThat(elements).containsExactly(2, 4, 6, 8);
    assertThat(elements2).containsExactly(2, 4, 6, 8);
  }
}
