package ar.unrn.tp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Data
@Getter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int codigo;
    @Version
    private Long version; // Campo de versión para control de concurrencia optimista

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private Marca marca;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name="idCategoria")
    private Categoria categoria;
    private double precio;
    private double porcentajeDeDescuento;

    public Producto(int codigo, String descripcion, Categoria categoria, double precio, Marca marca) {

        validarCodigo(codigo);
        validarDescripcion(descripcion);
        validarCategoria(categoria);
        validarPrecio(precio);

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.marca= marca;
    }


    public double precio() {
        return precio;
    }

    public Marca marca() {
        return marca;
    }

    public void asignarDescuento(double porcentajeDeDescuento) {
        this.porcentajeDeDescuento = porcentajeDeDescuento;
    }
    public double CalcularTotalConDescuento()
    {
        return precio-(precio*porcentajeDeDescuento);}


    public Long getId() {
        return id;
    }

    public int getCodigo() {
        return codigo;
    }

    public Long getVersion() {
        return version;
    }

    public Marca getMarca() {
        return marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public double getPorcentajeDeDescuento() {
        return porcentajeDeDescuento;
    }

    // validaciones
    private void validarCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código del producto debe ser un número positivo.");
        }
    }

    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción del producto no puede estar vacía.");
        }
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría del producto no puede ser nula.");
        }
    }

    private void validarPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor que cero.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(getId(), producto.getId()) && Objects.equals(getVersion(), producto.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVersion());
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
