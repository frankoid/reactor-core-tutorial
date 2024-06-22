package org.devrx;

import java.util.ArrayList;
import java.util.List;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class Main {

  public static void main(String[] args) {
//    Publisher<Integer> oneToFour = Flux.just(1, 2, 3, 4);

    List<Integer> elements = new ArrayList<>();

    Flux.just(1, 2, 3, 4).log().subscribe(elements::add);

    System.out.println(elements);

//    assertThat(elements).containsExactly(1, 2, 3, 4);

  }
}