package com.reactive.kafka;

import com.reactive.kafka.components.Consumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lanzador {

	public static void main(String[] args) {
		SpringApplication.run(Lanzador.class, args);
		Consumer consumidor = new Consumer();
		consumidor.suscribir("topicTest");

	}

}
