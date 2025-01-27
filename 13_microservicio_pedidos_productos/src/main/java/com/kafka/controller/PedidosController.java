package com.kafka.controller;

import com.kafka.model.Pedido;
import com.kafka.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidosController {

    @Autowired
    PedidosService pedidosService;

    @PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> nuevoPedido(@RequestBody Pedido pedido) {
        try {
            pedidosService.registrarPedido(pedido);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
