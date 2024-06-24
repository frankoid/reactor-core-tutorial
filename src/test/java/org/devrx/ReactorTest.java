package org.devrx;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
public class ReactorTest {
  @Test
  public void simpleSubscribe() {
    List<Integer> elements = new ArrayList<>();
    List<Integer> elements2 = new ArrayList<>();

    Flux<Integer> flux = Flux.just(1, 2, 3, 4)
        .log();
    flux.subscribe(elements::add);
    log.info("After subscribe(elements::add)");
    flux.subscribe(elements2::add);
    log.info("After subscribe(elements2::add)");

    assertThat(elements).containsExactly(1, 2, 3, 4);
    assertThat(elements2).containsExactly(1, 2, 3, 4);
  }
}
