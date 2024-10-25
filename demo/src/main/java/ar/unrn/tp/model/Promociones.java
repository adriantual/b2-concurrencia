package ar.unrn.tp.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public abstract class Promociones {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double porcentajeDeDescuento;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Promociones(LocalDate fechaInicio, LocalDate fechaFin, double porcentajeDeDescuento) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentajeDeDescuento = porcentajeDeDescuento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public abstract void aplicarDescuentoProducto(Producto unProducto);

    public abstract double aplicarDescuentoTarjeta(Tarjeta unaTarjeta);
    public boolean estaVigente() {
        LocalDate hoy = LocalDate.now();
        return (hoy.isEqual(fechaInicio) || hoy.isAfter(fechaInicio)) && (hoy.isEqual(fechaFin) || hoy.isBefore(fechaFin));
    }

    public double porcentajeDeDescuento() {
        return porcentajeDeDescuento;
    }
}
