package reactive.demo.service;

import reactive.demo.model.Elemento;
import reactor.core.publisher.Flux;

public interface ElementosService {
    Flux<Elemento> elementosPrecioMax(double precioMax);
}
