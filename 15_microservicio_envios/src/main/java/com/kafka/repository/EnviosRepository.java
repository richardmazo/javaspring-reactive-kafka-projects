package com.kafka.repository;

import com.kafka.model.Envio;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EnviosRepository extends ReactiveCrudRepository<Envio, Integer> {

    @Query("SELECT * FROM envios WHERE estado = 'pendiente'")
    Flux<Envio> findByPendientes();

}
