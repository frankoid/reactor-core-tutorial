package org.devrx;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class ReactorTest {

  @Test
  public void simpleSubscribe() {
    List<Integer> elements = new ArrayList<>();
    List<Integer> elements2 = new ArrayList<>();

    Flux.just(1, 2, 3, 4)
        .log()
        .subscribe(elements::add);

    assertThat(elements).containsExactly(1, 2, 3, 4);
  }
}
