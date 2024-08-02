package reactive.demo.repository;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;
import reactive.demo.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosRepository extends ReactiveCrudRepository<Producto, Integer> {

    Flux<Producto> findByCategoria(String categoria);

    @Modifying
    @Transactional
    Mono<Void> deleteByNombre(String name);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM productos WHERE precioUnitario > ?")
    Mono<Void> deletePrecio(double precioMax);

}
