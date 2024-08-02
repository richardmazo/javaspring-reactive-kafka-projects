package com.kafka.controller;

import com.kafka.service.EnviosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnviosController {

    @Autowired
    EnviosService enviosService;

    public ResponseEntity<?> enviosPendientes(){
        return new ResponseEntity<>(enviosService.pendientes(), HttpStatus.OK);
    }

}
