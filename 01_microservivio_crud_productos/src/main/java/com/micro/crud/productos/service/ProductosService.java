package com.micro.crud.productos.service;

import com.micro.crud.productos.model.Producto;

import java.util.List;

public interface ProductosService {

    List<Producto> catalogo();
    List<Producto> productosCategoria(String categoria);
    Producto productoCodigo(int cod);
    void altaProducto(Producto producto);
    Producto eliminarProducto(int cod);
    Producto actualizarPrecio(int cod, double precio);

}
