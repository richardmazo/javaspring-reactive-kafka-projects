package reactive.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
public class NamesController {

    @GetMapping(value="names")
    public Flux<String> getNames() {
        List<String> names = List.of("John", "Jane", "Doe","Alice");
        return Flux.fromIterable(names)
                .delayElements(Duration.ofSeconds(2));
    }

}
