package reactive.demo.service;

import org.springframework.stereotype.Service;
import reactive.demo.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductosServiceImpl implements ProductosService {

    private static List<Producto> productos = new ArrayList<>(List.of(new Producto(100,"Azucar","Alimentación",1.10,20),
            new Producto(101,"Leche","Alimentación",1.20,15),
            new Producto(102,"Jabón","Limpieza",0.89,30),
            new Producto(103,"Mesa","Hogar",125,4),
            new Producto(104,"Televisión","Hogar",650,10),
            new Producto(105,"Huevos","Alimentación",2.20,30),
            new Producto(106,"Fregona","Limpieza",3.40,6),
            new Producto(107,"Detergente","Limpieza",8.7,12)));

    @Override
    public Flux<Producto> catalogo() {
        return Flux.fromIterable(productos)
                .delayElements(Duration.ofMillis(500));//Flux<Producto>
    }

    @Override
    public Flux<Producto> productosCategoria(String categoria) {
        return catalogo()
                .filter(p -> p.getCategoria().equals(categoria));
    }

    @Override
    public Mono<Producto> productoCodigo(int cod) {
        return catalogo() //Flux<Producto>
                    .filter(p -> p.getCodProducto() == cod) //Flux<Producto>
                    .next();  //Mono<Producto>
                    //.switchIfEmpty(Mono.just(new Producto(500,"Producto por defecto","categoria por defecto",1.0, 10))); //Mono<Producto> o Mono.empty() si no hay producto con ese código

    }

    @Override
    public Mono<Void> altaProducto(Producto producto) {
        return productoCodigo(producto.getCodProducto()) //Mono<Producto>
                .switchIfEmpty(Mono.just(producto).map(p -> {
                    productos.add(p);
                    return p;
                })) //Mono<Producto>
                .then(); //Mono<Void>
    }

    @Override
    public Mono<Producto> eliminarProducto(int cod) {
        return productoCodigo(cod) //Mono<Producto>
                .map(p -> {
                    productos.removeIf(r -> r.getCodProducto() == cod);
                    return p;
                });//Mono<Producto>
                //.switchIfEmpty(null);
    }

    @Override
    public Mono<Producto> actualizarPrecio(int cod, double precio) {
        return productoCodigo(cod) //Mono<Producto>
                .map(p -> {
                    p.setPrecioUnitario(precio);
                    return p;
                });
    }
}
