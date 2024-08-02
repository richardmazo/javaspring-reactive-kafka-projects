package reactive.demo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactive.demo.model.Producto;
import reactive.demo.service.ProductosService;
import reactor.test.StepVerifier;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	ProductosService productosService;

	@Test
	@Order(1)
	void testProductosCategoria() {
		StepVerifier.create(productosService.productosCategoria("Alimentación"))
				.expectNextMatches(p -> p.getNombre().equals("Azucar"))
				.expectNextMatches(p -> p.getNombre().equals("Leche"))
				.expectNextMatches(p -> p.getNombre().equals("Huevos"))
				.verifyComplete();
	}

	@Test
	@Order(2)
	void testEliminarProducto() {
		StepVerifier.create(productosService.eliminarProducto(103))
				.expectNextMatches(p -> p.getNombre().equals("Mesa"))
				.verifyComplete();
	}

	@Test
	@Order(3)
	void testAltaProducto() {
		Producto pr = new Producto(250, "ptest", "cat1", 1, 2);
		StepVerifier.create(productosService.altaProducto(pr))
				.expectComplete()
				.verify();
	}

	@Test
	@Order(4)
	void testCatalogo(){
		StepVerifier.create(productosService.catalogo())
				.expectNextCount(8)
				.verifyComplete();
	}

}
