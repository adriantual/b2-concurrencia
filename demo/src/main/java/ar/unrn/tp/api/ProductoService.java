package ar.unrn.tp.api;

import ar.unrn.tp.model.*;
import ar.unrn.tp.model.DTO.ProductoDTO2;


import java.util.List;

public interface ProductoService {

    //validar que sea una categoría existente y que codigo no se repita
    void crearProducto(String codigo, String descripcion, double precio, Long categoria, Long marca);
    //validar que sea un producto existente
    void modificarProducto(Long idProducto, String codigo, String descripcion, double precio,  Long categoria, Long marca,Long version);

        //Devuelve todos los productos
    List listarProductos();

    List<Producto> buscarProductos(List<Long> idsProductos);

    List listarCategorias();
    List listarMarcas();

    ProductoDTO2 buscarProductoPorId(Long idProducto);

}
