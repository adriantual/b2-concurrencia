package ar.unrn.tp.model.DTO;



import ar.unrn.tp.model.Venta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import ar.unrn.tp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class VentaDTO {
    private Long clienteId;
    private LocalDate fecha;
    private List<Long> productos;
    private double total;
    private Long medioDePagoId;

   /* public VentaDTO() {
    }

    public VentaDTO(Long clienteId, LocalDate fecha, List<Long> productos, Long medioDePagoId) {
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.productos=productos;
        this.total = total;
        this.medioDePagoId = medioDePagoId;
    }
*/






/*
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<Long> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Long getMedioDePagoId() {
        return medioDePagoId;
    }

    public void setMedioDePagoId(Long medioDePagoId) {
        this.medioDePagoId = medioDePagoId;
    }*/
}
