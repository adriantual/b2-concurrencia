package ar.unrn.tp.model.DTO;



import java.time.LocalDate;

public class DescuentoMedioDePagoDTO {
    private String marcaTarjeta;
    private String fechaDesde;
    private String fechaHasta;
    private double porcentaje;

    public DescuentoMedioDePagoDTO() {
    }

    public DescuentoMedioDePagoDTO(String marcaTarjeta, String fechaDesde, String fechaHasta, double porcentaje) {
        this.marcaTarjeta = marcaTarjeta;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.porcentaje = porcentaje;
    }

    public String getMarcaTarjeta() {
        return marcaTarjeta;
    }

    public void setMarcaTarjeta(String marcaTarjeta) {
        this.marcaTarjeta = marcaTarjeta;
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
