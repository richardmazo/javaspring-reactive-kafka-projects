package com.kafka.service;

import com.kafka.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PedidosServiceImpl implements PedidosService {

    @Value("${topic}")
    String topico;

    @Autowired
    KafkaTemplate<String, Pedido> kafkaTemplate;

    @Override
    public void registrarPedido(Pedido pedido) {
        CompletableFuture<SendResult<String,Pedido>> future = kafkaTemplate.send(topico, pedido);
        future.whenCompleteAsync((result, ex) -> {
            if (ex != null) {
                throw new RuntimeException();
            }
            System.out.println("Se ha registrado el pedido " + result.getProducerRecord().value().getNombre() + " e el tópico " + result.getRecordMetadata().topic());
        });
    }

}
