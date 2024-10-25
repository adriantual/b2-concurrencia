package ar.unrn.tp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
public class PromocionPorProducto extends Promociones{

    @ManyToOne
    private Marca marca;

    public PromocionPorProducto(LocalDate fechaInicio, LocalDate fechaFin, double porcentajeDeDescuento, Marca marca) {
        super(fechaInicio, fechaFin, porcentajeDeDescuento);
        this.marca = marca;
    }




    @Override
    public void aplicarDescuentoProducto(Producto unProducto) {
        if (estaVigente()) {

            if (unProducto.marca().equals(this.marca)) {
                unProducto.asignarDescuento(this.porcentajeDeDescuento());
            }
        }

    }

    @Override
    public double aplicarDescuentoTarjeta(Tarjeta unaTarjeta) {
    return 0;
    }
}
