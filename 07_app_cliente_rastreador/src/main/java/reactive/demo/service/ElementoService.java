package reactive.demo.service;

import reactive.demo.model.Elemento;
import reactor.core.publisher.Flux;

public interface ElementoService {
    Flux<Elemento> elementosPorPrecio(double precioMax);
}
