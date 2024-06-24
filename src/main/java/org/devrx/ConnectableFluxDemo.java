package org.devrx;

import static java.time.Duration.ofSeconds;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ConnectableFluxDemo {

  public static void main(String[] args) {
    ConnectableFlux<Long> publish = Flux.<Long>create(fluxSink -> {
          while(true) {
            fluxSink.next(System.currentTimeMillis());
          }
        })
        .sample(ofSeconds(2))
        .publish();

    publish.subscribe(System.out::println);
    publish.subscribe(System.out::println);

    publish.connect();
  }
}