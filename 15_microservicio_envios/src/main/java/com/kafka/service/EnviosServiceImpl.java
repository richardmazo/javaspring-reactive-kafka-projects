package com.kafka.service;

import com.kafka.model.Envio;
import com.kafka.repository.EnviosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
public class EnviosServiceImpl implements EnviosService {

    @Autowired
    EnviosRepository enviosRepository;

    @Override
    public Flux<Envio> pendientes() {
        return enviosRepository.findByPendientes();
    }

    @KafkaListener(topics = "pedidosTopic", groupId = "myGroup2")
    public void gestionEnvios(Envio envio){
        envio.setFecha(LocalDateTime.now());
        envio.setEstado("pendiente");
        enviosRepository.save(envio)
                .subscribe();
    }

}
