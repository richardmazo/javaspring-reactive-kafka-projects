package reactive.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactive.demo.model.Producto;
import reactive.demo.service.ProductosService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
public class ProductosController {

    @Autowired
    ProductosService productosService;

    @GetMapping(value="productos")
    public ResponseEntity<Flux<Producto>> productos(){
        return new ResponseEntity<>(productosService.catalogo(), HttpStatus.OK);
    }

    @GetMapping(value="productos/{categoria}")
    public ResponseEntity<Flux<Producto>> productosCategoria(@PathVariable("categoria") String categoria){
        return new ResponseEntity<>(productosService.productosCategoria(categoria), HttpStatus.OK);
    }

    @GetMapping(value="producto")
    public ResponseEntity<Mono<Producto>> productoCodigo(@RequestParam("cod") int cod){
        return new ResponseEntity<>(productosService.productoCodigo(cod), HttpStatus.OK);
    }

    @PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Void>> altaProducto(@RequestBody Producto producto){
        producto.setNuevo(true);
        return new ResponseEntity<>(productosService.altaProducto(producto), HttpStatus.OK);
    }

    @DeleteMapping(value = "eliminar")
    public Mono<ResponseEntity<Producto>> eliminarProducto(@RequestParam("cod") int cod){
        return productosService.eliminarProducto(cod)//Mono<Producto>
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK)) //Mono<ResponseEntity<Producto>>
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND))); //Mono<ResponseEntity<Producto>>
    }

    @PutMapping(value = "actualizar")
    public Mono<ResponseEntity<Producto>> actualizarProducto(@RequestParam("cod") int cod, @RequestParam("precio") double precio){
        return productosService.actualizarPrecio(cod, precio)//Mono<Producto>
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))  //Mono<ResponseEntity<Producto>>
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND))); //Mono<ResponseEntity<Producto>>
    }

}
