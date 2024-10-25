package ar.unrn.tp.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@NoArgsConstructor
@Data
public class PromocionDeMedioDePago extends Promociones{

    private String bancoEmisor;

    public PromocionDeMedioDePago(LocalDate fechaInicio, LocalDate fechaFin, double porcentajeDeDescuento, String bancoEmisor) {
        super(fechaInicio, fechaFin, porcentajeDeDescuento);
        this.bancoEmisor = bancoEmisor;
    }


    @Override
    public void aplicarDescuentoProducto(Producto unProducto) {


    }

    @Override
    public double aplicarDescuentoTarjeta(Tarjeta unaTarjeta) {
        if (estaVigente()) {


            if (this.bancoEmisor.equals(unaTarjeta.marca())) {
                return this.porcentajeDeDescuento();
            }
        }
        return 0;
    }
}
