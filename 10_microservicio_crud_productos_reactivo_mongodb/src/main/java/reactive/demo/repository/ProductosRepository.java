package reactive.demo.repository;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactive.demo.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosRepository extends ReactiveCrudRepository<Producto, Integer> {

    Flux<Producto> findByCategoria(String categoria);

    Mono<Void> deleteByNombre(String name);

    @DeleteQuery(value = "{'precioUnitario': {$lt: ?0}}")
    Mono<Void> deletePrecio(double precioMax);

}
