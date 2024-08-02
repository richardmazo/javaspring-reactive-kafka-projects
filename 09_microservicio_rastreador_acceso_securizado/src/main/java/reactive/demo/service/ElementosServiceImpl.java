package reactive.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactive.demo.model.Elemento;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Base64;

@Service
public class ElementosServiceImpl implements ElementosService{

    String url1="http://localhost:8000";
    String url2="http://localhost:9000";

    @Value("${user}")
    String user;

    @Value("${password}")
    String password;

    @Override
    public Flux<Elemento> elementosPrecioMax(double precioMax) {
        Flux<Elemento> flux1 = catalogo(url1, "tienda 1");
        Flux<Elemento> flux2 = catalogo(url2, "tienda 2");
        return Flux.merge(flux1, flux2) //Flux<Elemento>
                .filter(elemento -> elemento.getPrecioUnitario() <= precioMax)
                .delayElements(Duration.ofSeconds(1));
    }

    private Flux<Elemento> catalogo(String url, String tienda){

        WebClient webClient = WebClient.create(url);
        return webClient.get()
                .uri("/productos")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization",getEncoderBase64Credentials(user,password))
                .retrieve()
                .bodyToFlux(Elemento.class)
                .map(elemento -> {
                    elemento.setTienda(tienda);
                    return elemento;
                });
    }

    private String getEncoderBase64Credentials(String user, String password){
        String userCredentials = user + ":" + password;
        return "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
    }

}