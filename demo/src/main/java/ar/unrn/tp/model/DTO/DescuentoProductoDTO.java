package ar.unrn.tp.model.DTO;



import java.time.LocalDate;

public class DescuentoProductoDTO {
    private Long marcaProducto;
    private String fechaDesde;
    private String fechaHasta;
    private double porcentaje;

    public DescuentoProductoDTO() {
    }

    public DescuentoProductoDTO(Long marcaProducto, String fechaDesde, String fechaHasta, double porcentaje) {
        this.marcaProducto = marcaProducto;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.porcentaje = porcentaje;
    }

    public Long getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(Long marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
