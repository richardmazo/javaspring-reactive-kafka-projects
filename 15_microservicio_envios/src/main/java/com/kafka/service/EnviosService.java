package com.kafka.service;

import com.kafka.model.Envio;
import reactor.core.publisher.Flux;

public interface EnviosService {
    Flux<Envio> pendientes();
}
