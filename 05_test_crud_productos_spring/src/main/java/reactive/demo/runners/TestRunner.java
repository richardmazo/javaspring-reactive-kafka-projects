package reactive.demo.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactive.demo.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        WebClient webClient = WebClient.create("http://localhost:8000");
        //Ejemplo 1 get productos
        /*Flux<Producto> flux = webClient.get()
                .uri("/productos")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Producto.class);
        flux.subscribe(System.out::println);*/

        //Ejemplo 2 create producto
        /*webClient.post()
                .uri("/alta")
                .body(Mono.just(new Producto(200,"prueba","categoria test",1.20,15)), Producto.class)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnTerminate(() -> System.out.println("Producto dado de alta, o no"))
                .block();*/

        //Ejemplo 3 get producto por código
        /*Mono<Producto> monoFind = webClient.get()
                .uri("/producto?cod=102")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Producto.class);

        monoFind.subscribe(p -> System.out.println("Producto encontrado: " + p));
        monoFind.switchIfEmpty(Mono.just(new Producto()).map( p -> {
            System.out.println("Producto no encontrado");
            return p;
        })).block();*/


        //Ejemplo 4 delete producto
        webClient.delete()
                .uri("/eliminar?cod=200")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(h -> h.is4xxClientError(), t -> {
                    System.out.println("Producto no encontrado");
                    return Mono.empty();
                })
                .bodyToMono(Producto.class)
                .subscribe(System.out::println);

    }
}
