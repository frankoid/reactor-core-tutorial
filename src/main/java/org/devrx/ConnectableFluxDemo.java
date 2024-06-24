package org.devrx;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ConnectableFluxDemo {

  public static void main(String[] args) {
    ConnectableFlux<Long> publish = Flux.<Long>create(fluxSink -> {
          while(true) {
            fluxSink.next(System.currentTimeMillis());
          }
        })
        .publish();

    publish.subscribe(System.out::println);
    publish.subscribe(System.out::println);

    publish.connect();
  }
}