package reactive.demo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Configuration
public class NamesController {

    @Bean
    public RouterFunction<ServerResponse> getNames() {
        List<String> names = List.of("John", "Jane", "Doe","Alice");
        return RouterFunctions.route(RequestPredicates.GET("/names"),
                request -> ServerResponse.ok() //BodyBuilder
                    .body(Flux.fromIterable(names)
                            .delayElements(Duration.ofSeconds(2)), String.class)//Mono<ServerResponse>
                    );//RouterFunction<ServerResponse>

    }

}
