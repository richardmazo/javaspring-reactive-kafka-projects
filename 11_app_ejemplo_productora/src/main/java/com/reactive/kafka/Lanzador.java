package com.reactive.kafka;

import com.reactive.kafka.components.Productor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class Lanzador {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Lanzador.class, args);
		Productor productor = new Productor();
		for (int i = 0; i < 10; i++) {
			productor.send("topicTest", "Mensaje generado a las " + LocalDateTime.now() + " para topicTest");
            Thread.sleep(100);
		}
		productor.cerrar();
	}

}
