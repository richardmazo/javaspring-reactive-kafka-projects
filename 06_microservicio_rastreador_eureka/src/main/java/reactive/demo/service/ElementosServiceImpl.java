package reactive.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactive.demo.model.Elemento;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class ElementosServiceImpl implements ElementosService{

    String url1="http://servicio-productos1";
    String url2="http://servicio-productos2";

    @Autowired
    WebClient.Builder builder;

    @Override
    public Flux<Elemento> elementosPrecioMax(double precioMax) {
        Flux<Elemento> flux1 = catalogo(url1, "tienda 1");
        Flux<Elemento> flux2 = catalogo(url2, "tienda 2");
        return Flux.merge(flux1, flux2) //Flux<Elemento>
                .filter(elemento -> elemento.getPrecioUnitario() <= precioMax)
                .delayElements(Duration.ofSeconds(1));
    }

    private Flux<Elemento> catalogo(String url, String tienda){

        WebClient webClient = builder.build();
        return webClient.get()
                .uri(url+"/productos")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Elemento.class)
                .map(elemento -> {
                    elemento.setTienda(tienda);
                    return elemento;
                });
    }

}