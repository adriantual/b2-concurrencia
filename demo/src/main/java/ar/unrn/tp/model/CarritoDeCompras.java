package ar.unrn.tp.model;

import java.util.ArrayList;
import java.util.List;

public class CarritoDeCompras {

    private Cliente cliente;
    private List<Producto> productos;
    private List<Promociones> promociones;

    public CarritoDeCompras(Cliente unCliente) {
        this.productos = new ArrayList<>();
        this.promociones = new ArrayList<>();
        this.cliente= unCliente;
    }

    public void agregarProducto(Producto unProducto)
    {
        this.productos.add(unProducto);
    }
    public void agregarMuchosProductos(List<Producto> productos){this.productos.addAll(productos);}
    public void agregarPromocion(Promociones unaPromo)
    {

        this.promociones.add(unaPromo);
    }

    public Venta procesoDePago(Tarjeta unaTarjeta, String numeroUnico)
    {
        double total = getTotal(unaTarjeta);

        return new Venta(this.productos, this.cliente, total, unaTarjeta, numeroUnico);

    }

    public double getTotal(Tarjeta unaTarjeta) {
        double total = 0;
        double descuentoPorMedioDePago=aplicarDescuentoPorMedioDePago(unaTarjeta);
        for (Producto unProducto: this.productos)
        {
            aplicarDescuentoPorProducto(unProducto);
            total += unProducto.CalcularTotalConDescuento();
        }


        total= total-(total*descuentoPorMedioDePago);
        return total;
    }

    private double aplicarDescuentoPorMedioDePago(Tarjeta unaTarjeta)
    {
        for(Promociones unaPromo: this.promociones)
        {
            return unaPromo.aplicarDescuentoTarjeta(unaTarjeta);
        }
        return 0;
    }
    public void aplicarDescuentoPorProducto(Producto unProducto) {
        for (Promociones unaPromo : this.promociones) {
            unaPromo.aplicarDescuentoProducto(unProducto);
        }
    }



}
