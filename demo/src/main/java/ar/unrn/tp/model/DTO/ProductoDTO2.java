package ar.unrn.tp.model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductoDTO2 {
    private Long id;
    private int codigo;
    private String descripcion;
    private double precio;
    private Long categoriaId;
    private String categoriaNombre;
    private Long marcaId;
    private String marcaNombre;
    private double porcentajeDeDescuento;

    private Long version;

    public ProductoDTO2(Long id, int codigo, String descripcion, double precio, Long categoriaId, String categoriaNombre, Long marcaId, String marcaNombre, double porcentajeDeDescuento, Long version) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.marcaId = marcaId;
        this.marcaNombre = marcaNombre;
        this.porcentajeDeDescuento = porcentajeDeDescuento;
        this.version=version;
    }

    public ProductoDTO2(int codigo, String descripcion, double precio, double porcentajeDeDescuento) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.porcentajeDeDescuento = porcentajeDeDescuento;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public double getPorcentajeDeDescuento() {
        return porcentajeDeDescuento;
    }

    public void setPorcentajeDeDescuento(double porcentajeDeDescuento) {
        this.porcentajeDeDescuento = porcentajeDeDescuento;
    }
}
